package com._thefull.dasom_app_demo.domain.promotion.domain.dto;


import com._thefull.dasom_app_demo.domain.promotion.domain.DiscType;
import com._thefull.dasom_app_demo.domain.promotion.domain.MenuPromotion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Builder
@AllArgsConstructor
public class SimpleMenuPromoResponseDTO {

    private Long id;

    private String status;

    private String menuName;

    private Integer price;
    private Integer discPrice;

    private LocalDate startDate;
    private LocalDate endDate;

    public static SimpleMenuPromoResponseDTO of(MenuPromotion e){
        /* discPrice 계산 */
        int price = e.getMenu().getPrice();
        double discPrice=0;
        DiscType _discType = e.getDiscType();
        if(_discType==DiscType.PERCENT){
            discPrice=(double) price*((100.0-(double) e.getDiscVal())/100.0);
        }else if(_discType==DiscType.PRICE){
            discPrice=price-e.getDiscVal();
        }else{
            discPrice=price;
        }

        return SimpleMenuPromoResponseDTO.builder()
                .id(e.getId())
                .status(e.getStatus().name())
                .menuName(e.getMenu().getName())
                .price(e.getMenu().getPrice())
                .discPrice((int)discPrice)
                .startDate(e.getStartDate())
                .endDate(e.getEndDate())
                .build();

    }

    public static List<SimpleMenuPromoResponseDTO> toDTOList(List<MenuPromotion> menuPromotionList){
        return menuPromotionList.stream()
                .map(SimpleMenuPromoResponseDTO::of)
                .collect(Collectors.toList());

    }

}
