package com._thefull.dasom_app_demo.promotion.domain.dto;


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
        return SimpleMenuPromoResponseDTO.builder()
                .menuPromoId(e.getId())
                .status(e.getStatus().name())
                .menuName(e.getMenu().getName())
                .menuPrice(e.getPrice())
                .discPrice(e.getDiscPrice())
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
