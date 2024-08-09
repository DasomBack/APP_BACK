package com._thefull.dasom_app_demo.domain.user.service;

import com._thefull.dasom_app_demo.domain.store.domain.Store;
import com._thefull.dasom_app_demo.domain.store.repository.StoreRepository;
import com._thefull.dasom_app_demo.domain.user.domain.User;
import com._thefull.dasom_app_demo.domain.user.domain.dto.ChangePasswordRequestDTO;
import com._thefull.dasom_app_demo.domain.user.domain.dto.UpdateUserRequestDTO;
import com._thefull.dasom_app_demo.domain.user.domain.dto.UserRegisterRequestDto;
import com._thefull.dasom_app_demo.domain.user.domain.dto.UserRegisterResponseDTO;
import com._thefull.dasom_app_demo.domain.user.repository.UserRepository;
import com._thefull.dasom_app_demo.global.exception.AppException;
import com._thefull.dasom_app_demo.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
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

    public UserRegisterResponseDTO updateUser(Long id, Store store, UpdateUserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_USER, "사용자를 찾을 수 없습니다"));
        user.update(dto);
        User updated = userRepository.save(user);

        return UserRegisterResponseDTO.of(updated,store);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


    public void changePassword(Long id, ChangePasswordRequestDTO dto) {
        String encodedPW = bCryptPasswordEncoder.encode(dto.getPassword());
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_USER, "사용자를 찾을 수 없습니다"));
        user.changePassword(encodedPW);

        userRepository.save(user);

    }
}

