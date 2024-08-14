package com._thefull.dasom_app_demo.domain.user.domain.dto;

import com._thefull.dasom_app_demo.domain.user.domain.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestDto {

    @NotBlank(message = "매장 코드를 입력해주세요")
    private String storeCode;

    @NotBlank(message = "성함을 입력해주세요")
    private String name;

//    @Email
//    private String email;

    @Nullable
    private String phoneNum;


    @Size(min = 6)
    private String password;

    public User toEntity(String encodedPW){
        return User.builder()
                .name(this.name)
                .phoneNum(this.phoneNum)
                .password(encodedPW)
                .build();
    }

}