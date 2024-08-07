package com._thefull.dasom_app_demo.domain.user.service;

import com._thefull.dasom_app_demo.domain.store.domain.Store;
import com._thefull.dasom_app_demo.domain.store.repository.StoreRepository;
import com._thefull.dasom_app_demo.domain.user.domain.User;
import com._thefull.dasom_app_demo.domain.user.domain.dto.UserRegisterRequestDto;
import com._thefull.dasom_app_demo.domain.user.domain.dto.UserRegisterResponseDTO;
import com._thefull.dasom_app_demo.domain.user.repository.UserRepository;
import com._thefull.dasom_app_demo.global.exception.AppException;
import com._thefull.dasom_app_demo.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegisterService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserRegisterResponseDTO registerUser(UserRegisterRequestDto dto){
        String encodedPW="";
        if (!dto.getPassword().isEmpty()){
            encodedPW = bCryptPasswordEncoder.encode(dto.getPassword());
        }else{
            throw new AppException(ErrorCode.NOT_FOUND_PASSWORD,"비밀번호가 입력되지 않았습니다. 다시 작성해 주십시오");
        }

        Store store = storeRepository.findByCode(dto.getStoreCode())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_STORE, "코드에 해당하는 매장이 존재하지 않습니다"));

        User newUser = dto.toEntity(encodedPW);
        User savedUser = userRepository.save(newUser);

        return UserRegisterResponseDTO.of(savedUser, store);


    }

}

