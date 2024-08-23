package com._thefull.dasom_app_demo.domain.promotion.domain.dto;

import com._thefull.dasom_app_demo.domain.menu.domain.Menu;
import com._thefull.dasom_app_demo.domain.menu.domain.dto.MenuResponseDTO;
import com._thefull.dasom_app_demo.domain.promotion.domain.DiscType;
import com._thefull.dasom_app_demo.domain.promotion.domain.MenuPromotion;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Long id;

    private MenuResponseDTO menu;

    private String status;

    private String category;

    // 할인 금액 타입
    // % 할인 : PERCENT
    // w(원) 할인 : PRICE
    // 없음 : NONE
    private String discType;

    // 할인값 or 할인율
    private Integer discVal;

    // 할인된 가격
    private Integer discPrice;

    // 행사 기간 타입
    // 상시 : ALWAYS
    // 2주 : TWO_WEEK
    // 1주 : ONE_WEEK
    // 그 외 : OTHER
    private String dateType;
    private LocalDate startDate;
    private LocalDate endDate;

    private Boolean isEqlOprTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime endTime;

    private Boolean isEqlPromoTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime mentStartTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime mentEndTime;

    private Integer mentInterval;

    // 할인 조건 추가 여부
    private Boolean isAddDiscCond;
    // 할인 추가 조건
    private String addDiscCond;

    // 제품 소개 추가 여부
    private Boolean isAddMenuDesc;
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
            discPrice=price*((100-e.getDiscVal())/100);
        }else if(_discType==DiscType.PRICE){
            discPrice=price-e.getDiscVal();
        }else{
            discPrice=price;
        }


        return MenuPromotionResponseDTO.builder()
                .id(e.getId())
                .menu(MenuResponseDTO.of(e.getMenu()))
                .status(e.getStatus().name())
                .category(e.getCategory().name())
                .discType(e.getDiscType().name())
                .discVal(e.getDiscVal())
                .discPrice(discPrice)
                .dateType(e.getDateType().name())
                .startDate(e.getStartDate())
                .endDate(e.getEndDate())
                .isEqlOprTime(e.getIsEqlOprTime())
                .startTime(e.getStartTime())
                .endTime(e.getEndTime())
                .isEqlPromoTime(e.getIsEqlPromoTime())
                .mentStartTime(e.getMentStartTime())
                .mentEndTime(e.getMentEndTime())
                .mentInterval(e.getMentInterval())
                .isAddDiscCond(e.getIsAddDiscCond())
                .addDiscCond(e.getAddDiscCond())
                .isAddMenuDesc(e.getIsAddMenuDesc())
                .addMenuDesc(e.getAddMenuDesc())
                .ment(e.getMent())
                .createdAt(e.getCreateAt())
                .modifiedAt(e.getModifiedAt())
                .build();

    }

}
