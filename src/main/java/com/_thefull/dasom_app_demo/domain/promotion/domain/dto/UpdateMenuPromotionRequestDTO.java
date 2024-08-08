package com._thefull.dasom_app_demo.domain.promotion.domain.dto;

import com._thefull.dasom_app_demo.domain.menu.domain.Menu;
import com._thefull.dasom_app_demo.domain.promotion.domain.DateType;
import com._thefull.dasom_app_demo.domain.promotion.domain.DiscType;
import com._thefull.dasom_app_demo.domain.promotion.domain.MenuPromotion;
import com._thefull.dasom_app_demo.domain.promotion.domain.Status;
import com._thefull.dasom_app_demo.domain.store.domain.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class UpdateMenuPromotionRequestDTO {

    // 메뉴 ID
    private Long menuId;

    private String status;

    // 할인 금액 타입
    // % 할인 : PERCENT
    // w(원) 할인 : PRICE
    // 없음 : NONE
    private String discType;

    // 할인값 혹은 할인율 : %인지는 백에서 확인 , 할인 없을 경우 -1
    private int discVal;

    // 행사 기간 타입
    // 상시 : ALWAYS
    // 2주 : WEEK2
    // 1주 : WEEK1
    // 그 외 : OTHER
    private String dateType;
    // 행사 기간
    private LocalDate promoStartDate;
    private LocalDate promoEndDate;

    // 행사 시간
    // 영업시간과 동일 여부
    private boolean boolEqlOprTime;
    private LocalTime promoStartTime;
    private LocalTime promoEndTime;

    // 멘트 발화 시간
    // 행사시간과 동일 여부
    private boolean boolEqlPromoTime;
    private LocalTime mentStartTime;
    private LocalTime mentEndTime;

    // 발화 횟수
    private int mentInterval;

    // 할인 조건 추가 여부
    private boolean boolAddDiscCond;
    // 할인 추가 조건
    private String addDiscCond;

    // 제품 소개 추가 여부
    private boolean boolAddMenuDesc;
    // 제품 추가 소개
    private String addMenuDesc;

    // AI 다솜 멘트
    private String ment;


    public MenuPromotion from(Menu menu,Store store){
        Status status = Status.fromStatusName(this.status);

        return MenuPromotion.builder()
                .use(true)
                .category(menu.getCategory())
                .status(status)
                .discType(DiscType.fromName(this.discType))
                .discVal(this.discVal)
                .dateType(DateType.fromName(this.dateType))
                .promoStartDate(this.promoStartDate)
                .promoEndDate(this.promoEndDate)
                .boolEqlOprTime(this.boolEqlOprTime)
                .promoStartTime(this.promoStartTime)
                .promoEndTime(this.promoEndTime)
                .boolEqlPromoTime(this.boolEqlPromoTime)
                .mentStartTime(this.mentStartTime)
                .mentEndTime(this.mentEndTime)
                .mentInterval(this.mentInterval)
                .boolAddDiscCond(this.boolAddDiscCond)
                .addMenuDesc(this.addMenuDesc)
                .boolAddDiscCond(this.boolAddDiscCond)
                .addDiscCond(this.addDiscCond)
                .ment(this.ment)
                .store(store)
                .build();

    }
}
