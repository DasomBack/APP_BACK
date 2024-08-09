package com._thefull.dasom_app_demo.domain.user.controller;


import com._thefull.dasom_app_demo.domain.store.domain.Store;
import com._thefull.dasom_app_demo.domain.user.domain.dto.ChangePasswordRequestDTO;
import com._thefull.dasom_app_demo.domain.user.domain.dto.UpdateUserRequestDTO;
import com._thefull.dasom_app_demo.domain.user.domain.dto.UserRegisterRequestDto;
import com._thefull.dasom_app_demo.domain.user.domain.dto.UserRegisterResponseDTO;
import com._thefull.dasom_app_demo.domain.user.service.UserRegisterService;
import com._thefull.dasom_app_demo.global.auth.AuthUser;
import com._thefull.dasom_app_demo.global.auth.LoginUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRegisterController {

    private final UserRegisterService userRegisterService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDTO> register(@RequestBody @Valid final UserRegisterRequestDto dto){

        UserRegisterResponseDTO response = userRegisterService.registerUser(dto);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<UserRegisterResponseDTO> updateUser(@AuthUser LoginUser user,
                                                              @RequestBody final UpdateUserRequestDTO dto){
        Store store = user.getStore();

        UserRegisterResponseDTO response = userRegisterService.updateUser(user.getId(),store, dto);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/password")
    public ResponseEntity<String> changePassword(@AuthUser LoginUser user,
                                                 @RequestBody final ChangePasswordRequestDTO dto){
        userRegisterService.changePassword(user.getId(), dto);

        return ResponseEntity.ok().body("비밀번호가 변경되었습니다");

    }

    @DeleteMapping
    public ResponseEntity<String> resignUser(@AuthUser LoginUser user){
        userRegisterService.deleteUserById(user.getId());

        return ResponseEntity.ok().body("ID = "+user.getId() + " 회원 정상적으로 탈퇴되었습니다");
    }


}
