package com._thefull.dasom_app_demo.domain.store.controller;

import com._thefull.dasom_app_demo.global.exception.AppException;
import com._thefull.dasom_app_demo.global.exception.ErrorCode;
import com._thefull.dasom_app_demo.domain.store.domain.Store;
import com._thefull.dasom_app_demo.domain.store.domain.dto.StoreTimeResponseDTO;
import com._thefull.dasom_app_demo.domain.store.repository.StoreRepository;
import com._thefull.dasom_app_demo.domain.store.service.StoreService;
import com._thefull.dasom_app_demo.domain.user.domain.User;
import com._thefull.dasom_app_demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    /****************** 로그인 개발하기 전 테스트용 ***********************/
    private final UserRepository userRepository;
    private User defaultTestUser(){
        User user = userRepository.findById(1l)
                .orElseThrow(() -> new AppException(ErrorCode.NO_CATEGORY, ""));

        return user;
    }

    private final StoreRepository storeRepository;
    private Store defaultTestStore(){
        Store store = storeRepository.findById(1l)
                .orElseThrow(() -> new AppException(ErrorCode.NO_CATEGORY, ""));

        return store;
    }
    /*****************************************************************/

    @GetMapping("/time")
    public ResponseEntity<StoreTimeResponseDTO> getStoreTime(){
        Store store = defaultTestStore();
        StoreTimeResponseDTO response = storeService.findStoreOperatingTime(store);

        return ResponseEntity.ok().body(response);
    }


}
