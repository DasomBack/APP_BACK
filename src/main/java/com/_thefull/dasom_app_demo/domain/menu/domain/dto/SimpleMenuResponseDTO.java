package com._thefull.dasom_app_demo.domain.menu.domain.dto;

import com._thefull.dasom_app_demo.domain.menu.domain.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @Builder
@AllArgsConstructor
public class SimpleMenuResponseDTO {

    private Long id;
    private String name;
    private int price;
    private String imgUrl;

    public static SimpleMenuResponseDTO of(Menu e){
        return SimpleMenuResponseDTO.builder()
                .id(e.getId())
                .name(e.getName())
                .price(e.getPrice())
                .imgUrl(e.getImgUrl())
                .build();
    }
}
