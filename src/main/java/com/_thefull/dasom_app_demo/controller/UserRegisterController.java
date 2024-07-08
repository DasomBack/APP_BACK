package com._thefull.dasom_app_demo.controller;


import com._thefull.dasom_app_demo.domain.dto.UserRegisterRequestDto;
import com._thefull.dasom_app_demo.domain.dto.UserRegisterResponseDto;
import com._thefull.dasom_app_demo.service.UserRegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserRegisterController {

    private final UserRegisterService userRegisterService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> register(@RequestBody @Valid UserRegisterRequestDto dto){

        System.out.println("UserRegisterController.register");
        System.out.println(dto.getEmail());
        userRegisterService.registerUser(dto);

        return ResponseEntity.ok().body(UserRegisterResponseDto.toResponse(dto));
    }
}
