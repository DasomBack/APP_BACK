package com._thefull.dasom_app_demo.domain.dto;

import com._thefull.dasom_app_demo.domain.User;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestDto {

    @NotBlank(message = "매장명을 입력해주세요")
    private String store;

    @NotBlank(message = "성함을 입력해주세요")
    private String name;

    @Email
    private String email;

    @Nullable
    private String phoneNum;

    @Size(min = 6)
    private String password;

    public User toEntity(){
        return User.builder()
                .store(this.store)
                .email(this.email)
                .name(this.name)
                .phoneNum(this.phoneNum)
                .password(this.password)
                .build();
    }

}