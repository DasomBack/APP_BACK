package com._thefull.dasom_app_demo.domain.menu.domain.dto;

import com._thefull.dasom_app_demo.domain.menu.domain.Menu;
import com._thefull.dasom_app_demo.domain.menu.domain.TempMenuIngred;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Builder
@AllArgsConstructor
public class MenuResponseDTO {

    private Long id;
    private String name;
    private int price;
    private int basePrice;
    private String imgUrl;
    private String desc;

    private List<TempIngredResponseDTO> ingredients;

    public static MenuResponseDTO of(Menu e){
        return MenuResponseDTO.builder()
                .id(e.getId())
                .name(e.getName())
                .price(e.getPrice())
                .basePrice(e.getBasePrice())
                .imgUrl(e.getImgUrl())
                .desc(e.getDesc())
                .ingredients(e.getIngredList().stream().map(TempIngredResponseDTO::of).collect(Collectors.toList()))
                .build();
    }


}
