package com._thefull.dasom_app_demo.domain.store.domain.dto;

import com._thefull.dasom_app_demo.domain.store.domain.StoreOperatingHours;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter @Builder
@AllArgsConstructor
public class StoreTimeResponseDTO {

    private Long storeId;

    private String day;
    private boolean boolIsOpr;

    private LocalTime openTime;
    private LocalTime closeTime;

    private LocalTime breakStartTime;
    private LocalTime breakEndTime;

    public static StoreTimeResponseDTO from(StoreOperatingHours e){
        return StoreTimeResponseDTO.builder()
                .storeId(e.getId())
                .day(e.getDay().name())
                .boolIsOpr(e.getBoolIsOpr())
                .openTime(e.getOpenTime())
                .closeTime(e.getCloseTime())
                .breakStartTime(e.getBreakStartTime())
                .breakEndTime(e.getBreakEndTime())
                .build();

    }

}
