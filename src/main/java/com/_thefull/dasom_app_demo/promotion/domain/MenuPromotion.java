package com._thefull.dasom_app_demo.promotion.domain;

import com._thefull.dasom_app_demo.global.Status;
import com._thefull.dasom_app_demo.global.StatusConverter;
import com._thefull.dasom_app_demo.menu.domain.Category;
import com._thefull.dasom_app_demo.menu.domain.CategoryConverter;
import com._thefull.dasom_app_demo.menu.domain.Menu;
import com._thefull.dasom_app_demo.store.domain.Store;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "MENU_PROMOTIONS")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuPromotion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_PROMO_ID", updatable = false)
    private Long id;

    // 프로모션 사용 여부
    @Column(name = "`USE`")
    private Boolean use;

    // 카테고리
    @Setter
    @Convert(converter = CategoryConverter.class)
    @Column(name = "category" , columnDefinition = "TINYINT")
    private Category category;

    // 상태
    @Setter
    @Convert(converter = StatusConverter.class)
    @Column(name = "STATUS" , columnDefinition = "TINYINT")
    private Status status;

    //정가
    @Column(name = "PRICE")
    @NotNull
    private int price;

    // 할인 적용 가격 : 할인이 적용된 가격 ex) 5000원 -> 4000원
    @Column(name = "DISC_PRICE")
    private int discPrice;

    // 할인값(원) ex ) 1000원
    @Builder.Default
    @Column(name = "DISC_VAL")
    private Integer discVal=0;

    // 할인율(%)
    @Builder.Default
    @Column(name = "DISC_RATE")
    private Integer discRate=0;

    // 할인율-discRate인지 아닌지(할인값-discVal)여부
    @Transient
    private Boolean isDiscRate;

    // 프로모션 시작일
    @Column(name = "START_DATE")
    private LocalDate startDate;

    // 프로모션 종료일
    @Column(name = "END_DATE")
    private LocalDate endDate;

    // 프로모션 시작 시간
    @Column(name = "START_TIME")
    private LocalTime startTime;

    // 프로모션 종료 시간
    @Column(name = "END_TIME")
    private LocalTime endTime;

    // 멘트 발화 시간
    @Column(name = "MENT_START_TIME")
    private LocalTime mentStartTime;

    // 멘트 발화 종료 시간
    @Column(name = "MENT_END_TIME")
    private LocalTime mentEndTime;

    // 발화 빈도수
    @Column(name = "MENT_FREQ")
    private int mentFreq;

    // 프로모션 소개 추가 여부
    @Column(name = "IS_ADD_DESC")
    private Boolean isAddDesc;

    // 프로모션 추가 소개
    @Column(name = "ADD_DESC", columnDefinition = "TINYTEXT")
    private String addDesc;

    //할인 여부 : 할인하지 않은 상품도 홍보할 수 있습니다.
    @Column(name = "IS_DISC")
    private Boolean isDisc;


    // 할인 조건 추가 여부
    @Column(name = "IS_ADD_COND")
    private Boolean isAddCond;

    // 할인 조건 추가한 내용
    @Column(name = "ADD_DISC_COND", length = 1000)
    private String addDiscCond;

    // 다솜 멘트
    @Column(name = "MENT", columnDefinition = "TEXT")
    private String ment;

//    @Column(name = "MENT_COUNT", columnDefinition = "TINYINT")
//    private int mentCount;


    // 상시 여부
    @Column(name = "IS_ALWAYS")
    private Boolean isAlways;

    // 매장
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    // 메뉴
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENU_ID")
    private Menu menu;



}
