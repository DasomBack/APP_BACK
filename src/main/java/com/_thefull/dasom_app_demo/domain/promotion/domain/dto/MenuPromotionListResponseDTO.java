package com._thefull.dasom_app_demo.domain.promotion.domain.dto;

import com._thefull.dasom_app_demo.global.Status;
import com._thefull.dasom_app_demo.domain.menu.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
public class MenuPromotionListResponseDTO {

    private String category;
    private String status;

    private List<SimpleMenuPromoResponseDTO> menuPromoList;

    public static MenuPromotionListResponseDTO of(Category category, Status status, List<SimpleMenuPromoResponseDTO> menuPromoList){
        return MenuPromotionListResponseDTO.builder()
                .category(category.name())
                .status(status.name())
                .menuPromoList(menuPromoList)
                .build();
    }


}
