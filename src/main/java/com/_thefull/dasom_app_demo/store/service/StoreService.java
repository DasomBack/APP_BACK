package com._thefull.dasom_app_demo.store.service;


import com._thefull.dasom_app_demo.global.exception.AppException;
import com._thefull.dasom_app_demo.global.exception.ErrorCode;
import com._thefull.dasom_app_demo.store.domain.Store;
import com._thefull.dasom_app_demo.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service @Transactional
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public Store findById(Long storeId){
        return storeRepository.findById(storeId).orElseThrow(()->new AppException(ErrorCode.NOT_FOUND_STORE, "매장을 찾을 수 없습니다"));
    }


}
