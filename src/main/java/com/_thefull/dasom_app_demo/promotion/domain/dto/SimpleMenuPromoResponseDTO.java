package com._thefull.dasom_app_demo.promotion.domain.dto;


import com._thefull.dasom_app_demo.promotion.domain.DiscType;
import com._thefull.dasom_app_demo.promotion.domain.MenuPromotion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Builder
@AllArgsConstructor
public class SimpleMenuPromoResponseDTO {

    private Long menuPromoId;

    private String status;

    private String menuName;

    private int menuPrice;
    private int discPrice;

    private LocalDate startDate;
    private LocalDate endDate;

    public static SimpleMenuPromoResponseDTO of(MenuPromotion e){
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

        return SimpleMenuPromoResponseDTO.builder()
                .menuPromoId(e.getId())
                .status(e.getStatus().name())
                .menuName(e.getMenu().getName())
                .menuPrice(e.getMenu().getPrice())
                .discPrice(discPrice)
                .startDate(e.getPromoStartDate())
                .endDate(e.getPromoEndDate())
                .build();

    }

    public static List<SimpleMenuPromoResponseDTO> toDTOList(List<MenuPromotion> menuPromotionList){
        return menuPromotionList.stream()
                .map(SimpleMenuPromoResponseDTO::of)
                .collect(Collectors.toList());

    }

}
