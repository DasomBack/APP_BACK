package com._thefull.dasom_app_demo;

import com._thefull.dasom_app_demo.domain.user.domain.User;
import com._thefull.dasom_app_demo.global.auth.AuthUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testcontroller {

    @GetMapping("/test")
    public ResponseEntity<String> authenticationTest(@AuthUser User user){
        System.out.println("testcontroller.authenticationTest");
        System.out.println(user.getEmail());
        System.out.println(user.getPhoneNum());

        return ResponseEntity.ok().body("로그인이 수행되었습니다");
    }
}
