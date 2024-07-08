package com._thefull.dasom_app_demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserRegisterResponseDto {
    private String name;
    private String store;
    private String email;

    public static UserRegisterResponseDto toResponse(UserRegisterRequestDto dto){
        return UserRegisterResponseDto.builder()
                .name(dto.getName())
                .store(dto.getStore())
                .email(dto.getEmail())
                .build();
    }
}
