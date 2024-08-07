package com._thefull.dasom_app_demo.domain.user.controller;


import com._thefull.dasom_app_demo.domain.user.domain.dto.UserRegisterRequestDto;
import com._thefull.dasom_app_demo.domain.user.domain.dto.UserRegisterResponseDTO;
import com._thefull.dasom_app_demo.domain.user.service.UserRegisterService;
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
    public ResponseEntity<UserRegisterResponseDTO> register(@RequestBody @Valid UserRegisterRequestDto dto){

        System.out.println("UserRegisterController.register");
        System.out.println(dto.getEmail());
        UserRegisterResponseDTO response = userRegisterService.registerUser(dto);

        return ResponseEntity.ok().body(response);
    }
}
