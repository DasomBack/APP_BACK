package com._thefull.dasom_app_demo.domain.menu.domain.dto;

import com._thefull.dasom_app_demo.domain.menu.domain.TempMenuIngred;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TempIngredResponseDTO
{
    private String name;
    private int price;
    private String unit;

    public static TempIngredResponseDTO of(TempMenuIngred e){
        return TempIngredResponseDTO.builder()
                .name(e.getName())
                .price(e.getPrice())
                .unit(e.getUnit())
                .build();
    }

}
