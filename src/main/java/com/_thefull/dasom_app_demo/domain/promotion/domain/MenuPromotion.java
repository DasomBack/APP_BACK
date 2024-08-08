package com._thefull.dasom_app_demo.domain.promotion.domain;

import com._thefull.dasom_app_demo.global.BaseEntity;
import com._thefull.dasom_app_demo.domain.menu.domain.Category;
import com._thefull.dasom_app_demo.domain.menu.domain.CategoryConverter;
import com._thefull.dasom_app_demo.domain.menu.domain.Menu;
import com._thefull.dasom_app_demo.domain.promotion.domain.dto.MenuPromotionRequestDTO;
import com._thefull.dasom_app_demo.domain.promotion.service.DateTypeConverter;
import com._thefull.dasom_app_demo.domain.promotion.service.DiscTypeConverter;
import com._thefull.dasom_app_demo.domain.store.domain.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "MENU_PROMOTIONS")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuPromotion extends BaseEntity {

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
    @Convert(converter = StatusConverter.class)
    @Column(name = "STATUS" , columnDefinition = "TINYINT")
    private Status status;

//    //정가
//    @Column(name = "PRICE")
//    @NotNull
//    private int price;

//    // 할인 적용 가격 : 할인이 적용된 가격 ex) 5000원 -> 4000원
//    @Column(name = "DISC_PRICE")
//    private int discPrice;

    @Convert(converter = DateTypeConverter.class)
    @Column(name = "DISC_TYPE", columnDefinition = "TINYINT")
    private DiscType discType;

    // 할인값 or 할인율
    @Builder.Default
    @Column(name = "DISC_VAL")
    private Integer discVal=0;

//    // 할인율(%)
//    @Builder.Default
//    @Column(name = "DISC_RATE")
//    private Integer discRate=0;

    @Convert(converter = DiscTypeConverter.class)
    @Column(name = "DATE_TYPE", columnDefinition = "TINYINT")
    private DateType dateType;

    // 프로모션 시작일
    @Column(name = "START_DATE")
    private LocalDate promoStartDate;

    // 프로모션 종료일
    @Column(name = "END_DATE")
    private LocalDate promoEndDate;

    // 영업시간과 동일 여부
    @Column(name = "IS_EQL_OPR_TIME")
    private Boolean boolEqlOprTime;

    // 프로모션 시작 시간
    @Column(name = "START_TIME")
    private LocalTime promoStartTime;

    // 프로모션 종료 시간
    @Column(name = "END_TIME")
    private LocalTime promoEndTime;

    // 행사시간과 동일 여부
    @Column(name = "IS_EQL_PROMO_TIME")
    private Boolean boolEqlPromoTime;

    // 멘트 발화 시간
    @Column(name = "MENT_START_TIME")
    private LocalTime mentStartTime;

    // 멘트 발화 종료 시간
    @Column(name = "MENT_END_TIME")
    private LocalTime mentEndTime;

    // 발화 빈도수
    @Column(name = "MENT_INTERVAL")
    private int mentInterval;

    // 프로모션 소개 추가 여부
    @Column(name = "IS_ADD_DESC")
    private Boolean boolAddMenuDesc;

    // 프로모션 추가 소개
    @Column(name = "ADD_DESC", columnDefinition = "TINYTEXT")
    private String addMenuDesc;

//    //할인 여부 : 할인하지 않은 상품도 홍보할 수 있습니다.
//    @Column(name = "IS_DISC")
//    private Boolean boolIsDisc;


    // 할인 조건 추가 여부
    @Column(name = "IS_ADD_COND")
    private Boolean boolAddDiscCond;

    // 할인 조건 추가한 내용
    @Column(name = "ADD_DISC_COND", length = 1000)
    private String addDiscCond;

    // 다솜 멘트
    @Column(name = "MENT", columnDefinition = "TEXT")
    private String ment;

//    @Column(name = "MENT_COUNT", columnDefinition = "TINYINT")
//    private int mentCount;

//    // 상시 여부
//    @Column(name = "IS_ALWAYS")
//    private Boolean boolIsAlways;

    // 매장
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    // 메뉴
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENU_ID")
    private Menu menu;

    public void updateStatus(Status status){
        this.status=status;
    }

    public void updateMenuPromotion(MenuPromotionRequestDTO dto, Menu menu){
        Status _status = Status.determinStatusFromDate(dto.getPromoStartDate(), dto.getPromoEndDate());

        this.category=menu.getCategory();
        this.status=_status;
        this.discType=DiscType.fromName(dto.getDateType());
        this.discVal=dto.getDiscVal();
        this.dateType= DateType.fromName(dto.getDateType());
        this.promoStartDate=dto.getPromoStartDate();
        this.promoEndDate=dto.getPromoEndDate();
        this.boolEqlOprTime=dto.isBoolEqlOprTime();
        this.promoStartTime = dto.getPromoStartTime();
        this.promoEndTime=dto.getPromoEndTime();
        this.boolEqlPromoTime=dto.isBoolEqlPromoTime();
        this.mentStartTime=dto.getMentStartTime();
        this.mentEndTime=dto.getMentEndTime();
        this.mentInterval=dto.getMentInterval();
        this.boolAddDiscCond=dto.isBoolAddDiscCond();
        this.addDiscCond=dto.getAddDiscCond();
        this.boolAddMenuDesc=dto.isBoolAddMenuDesc();
        this.addMenuDesc=dto.getAddMenuDesc();
        this.ment= dto.getMent();
        this.menu=menu;

    }



}
