package com._thefull.dasom_app_demo.store.service;


import com._thefull.dasom_app_demo.global.exception.AppException;
import com._thefull.dasom_app_demo.global.exception.ErrorCode;
import com._thefull.dasom_app_demo.store.domain.Day;
import com._thefull.dasom_app_demo.store.domain.Store;
import com._thefull.dasom_app_demo.store.domain.StoreOperatingHours;
import com._thefull.dasom_app_demo.store.domain.dto.StoreTimeResponseDTO;
import com._thefull.dasom_app_demo.store.repository.StoreOperatingHoursRepository;
import com._thefull.dasom_app_demo.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service @Transactional
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreOperatingHoursRepository storeOperatingHoursRepository;

    public Store findById(Long storeId){
        return storeRepository.findById(storeId).orElseThrow(()->new AppException(ErrorCode.NOT_FOUND_STORE, "매장을 찾을 수 없습니다"));
    }


    public StoreTimeResponseDTO findStoreOperatingTime(Store store) {
        int intVal = LocalDate.now().getDayOfWeek().getValue();
        Day day = Day.fromIntVal(intVal);
        StoreOperatingHours todaysOperatingTime = storeOperatingHoursRepository.findByDay(day)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_DAY, "유효하지 않은 요일입니다"));

        return StoreTimeResponseDTO.from(todaysOperatingTime);
    }
}
