package com._thefull.dasom_app_demo.domain.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDTO {

    private String name;
    private String phoneNum;

}
