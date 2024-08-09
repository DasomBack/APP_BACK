package com._thefull.dasom_app_demo.domain.store.controller;

import com._thefull.dasom_app_demo.global.auth.AuthUser;
import com._thefull.dasom_app_demo.global.auth.LoginUser;
import com._thefull.dasom_app_demo.global.exception.AppException;
import com._thefull.dasom_app_demo.global.exception.ErrorCode;
import com._thefull.dasom_app_demo.domain.store.domain.Store;
import com._thefull.dasom_app_demo.domain.store.domain.dto.StoreTimeResponseDTO;
import com._thefull.dasom_app_demo.domain.store.repository.StoreRepository;
import com._thefull.dasom_app_demo.domain.store.service.StoreService;
import com._thefull.dasom_app_demo.domain.user.domain.User;
import com._thefull.dasom_app_demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/time")
    public ResponseEntity<StoreTimeResponseDTO> getStoreTime(@AuthUser LoginUser user){
        Store store = user.getStore();
        StoreTimeResponseDTO response = storeService.findStoreOperatingTime(store);

        return ResponseEntity.ok().body(response);
    }


}
