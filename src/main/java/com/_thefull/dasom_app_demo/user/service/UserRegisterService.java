package com._thefull.dasom_app_demo.user.service;

import com._thefull.dasom_app_demo.user.domain.User;
import com._thefull.dasom_app_demo.user.domain.dto.UserRegisterRequestDto;
import com._thefull.dasom_app_demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegisterService {

    private final UserRepository userRepository;

    public void registerUser(UserRegisterRequestDto dto){
        User newUser = dto.toEntity();
        userRepository.save(newUser);

        System.out.println("UserRegisterService.registerUser");

    }

}

