package com._thefull.dasom_app_demo.domain.store.domain.dto;

import com._thefull.dasom_app_demo.domain.store.domain.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class StoreResponseDTO {

    private Long id;
    private String name;
    private String companyName;
    private String instagramLink;
    private String phoneNum;
    private String code;

    public static StoreResponseDTO of(Store e){
        return StoreResponseDTO.builder()
                .id(e.getId())
                .name(e.getName())
                .companyName(e.getCompanyName())
                .instagramLink(e.getInstagramLink())
                .phoneNum(e.getPhoneNum())
                .code(e.getCode())
                .build();
    }
}
