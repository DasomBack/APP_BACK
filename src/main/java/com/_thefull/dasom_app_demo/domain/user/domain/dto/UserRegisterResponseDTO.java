package com._thefull.dasom_app_demo.domain.user.domain.dto;

import com._thefull.dasom_app_demo.domain.store.domain.Store;
import com._thefull.dasom_app_demo.domain.store.domain.dto.StoreResponseDTO;
import com._thefull.dasom_app_demo.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserRegisterResponseDTO {

    private Long id;
    private String name;
    private StoreResponseDTO store;
    private String email;
    private String phoneNum;

    public static UserRegisterResponseDTO of(User e, Store store){
        return UserRegisterResponseDTO.builder()
                .id(e.getUserId())
                .name(e.getName())
                .store(StoreResponseDTO.of(store))
                .email(e.getEmail())
                .phoneNum(e.getPhoneNum())
                .build();
    }
}
