package com._thefull.dasom_app_demo.global.auth;

import com._thefull.dasom_app_demo.domain.store.domain.Store;
import com._thefull.dasom_app_demo.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter
public class LoginUser {

    private long id;

    private String name;
    private String phoneNum;
    private String imgUrl;
    private String password;

    private Store store;


    public static LoginUser from(User e, Store store){
        return LoginUser.builder()
                .id(e.getUserId())
                .name(e.getName())
                .phoneNum(e.getPhoneNum())
                .imgUrl(e.getImgUrl())
                .password(e.getPassword())
                .store(store)
                .build();

    }
}
