package com._thefull.dasom_app_demo.domain.store.domain;

import com._thefull.dasom_app_demo.global.BaseEntity;
import com._thefull.dasom_app_demo.domain.menu.domain.Menu;
import com._thefull.dasom_app_demo.domain.promotion.domain.MenuPromotion;
import com._thefull.dasom_app_demo.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Builder
@Getter
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STORE_ID")
    private Long id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "COMPANY_NAME",length = 50)
    private String companyName;

    @Column(name = "INSTAGRAM_LINK")
    private String instagramLink;

    @Column(name = "PHONE_NUM")
    private String phoneNum;

    @Column(name = "CODE")
    private String code;

//    @CreatedDate
//    @Column(name = "REGISTER_DATE", updatable = false, columnDefinition = "TIMESTAMP")
//    private LocalDateTime registerDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Menu> menuList =new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<MenuPromotion> menuPromotionList = new ArrayList<>();

    public void changeUser(User user){
        this.user=user;
    }
}
