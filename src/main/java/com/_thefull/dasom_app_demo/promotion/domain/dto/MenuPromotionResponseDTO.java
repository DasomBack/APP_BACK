package com._thefull.dasom_app_demo.promotion.domain.dto;

import com._thefull.dasom_app_demo.menu.domain.Menu;
import com._thefull.dasom_app_demo.promotion.domain.DiscType;
import com._thefull.dasom_app_demo.promotion.domain.MenuPromotion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@Builder
public class MenuPromotionResponseDTO {
    private Long menuPromoId;

    private Menu menu;

    private String status;

    private String category;

    // 할인 금액 타입
    // % 할인 : PERCENT
    // w(원) 할인 : PRICE
    // 없음 : NONE
    private String discType;

    // 할인값 or 할인율
    private int discVal;

    // 할인된 가격
    private int discPrice;

    // 행사 기간 타입
    // 상시 : ALWAYS
    // 2주 : 2WEEK
    // 1주 : 1WEEK
    // 그 외 : OTHER
    private String dateType;
    private LocalDate promoStartDate;
    private LocalDate promoEndDate;

    private boolean boolEqlOprTime;
    private LocalTime promoStartTime;
    private LocalTime promoEndTime;

    private boolean boolEqlPromoTime;
    private LocalTime mentStartTime;
    private LocalTime mentEndTime;

    private String mentInterval;

    // 할인 조건 추가 여부
    private boolean boolAddDiscCond;
    // 할인 추가 조건
    private String addDiscCond;

    // 제품 소개 추가 여부
    private boolean boolAddMenuDesc;
    // 제품 추가 소개
    private String addMenuDesc;

    private String ment;

    private Timestamp createdAt;
    private Timestamp modifiedAt;


    public static MenuPromotionResponseDTO from(MenuPromotion e){
        /* discPrice 계산 */
        int price = e.getMenu().getPrice();
        int discPrice=0;
        DiscType _discType = e.getDiscType();
        if(_discType==DiscType.PERCENT){
            discPrice=price*(100-e.getDiscVal());
        }else if(_discType==DiscType.PRICE){
            discPrice=price-e.getDiscVal();
        }else{
            discPrice=price;
        }

        /* mentInterval string 생성 */
        int _mentInterval = e.getMentInterval();
        String mentInterval=_mentInterval+"분 간격";

        return MenuPromotionResponseDTO.builder()
                .menuPromoId(e.getId())
                .menu(e.getMenu())
                .status(e.getStatus().name())
                .category(e.getCategory().name())
                .discType(e.getDiscType().name())
                .discVal(e.getDiscVal())
                .discPrice(discPrice)
                .dateType(e.getDateType().name())
                .promoStartDate(e.getPromoStartDate())
                .promoEndDate(e.getPromoEndDate())
                .boolEqlOprTime(e.getBoolEqlOprTime())
                .promoStartTime(e.getPromoStartTime())
                .promoEndDate(e.getPromoEndDate())
                .boolEqlPromoTime(e.getBoolEqlPromoTime())
                .mentStartTime(e.getMentStartTime())
                .mentEndTime(e.getMentEndTime())
                .mentInterval(mentInterval)
                .boolAddDiscCond(e.getBoolAddDiscCond())
                .addDiscCond(e.getAddDiscCond())
                .boolAddMenuDesc(e.getBoolAddMenuDesc())
                .addMenuDesc(e.getAddMenuDesc())
                .ment(e.getMent())
                .createdAt(e.getCreateAt())
                .modifiedAt(e.getModifiedAt())
                .build();

    }

}
