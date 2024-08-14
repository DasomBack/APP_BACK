package com._thefull.dasom_app_demo.domain.store.domain.dto;

import com._thefull.dasom_app_demo.domain.store.domain.StoreOperatingHours;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter @Builder
@AllArgsConstructor
public class StoreTimeResponseDTO {

    private Long storeId;

    private String day;
    private Boolean isOpr;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime openTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime closeTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime breakStartTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime breakEndTime;

    public static StoreTimeResponseDTO from(StoreOperatingHours e){
        return StoreTimeResponseDTO.builder()
                .storeId(e.getId())
                .day(e.getDay().name())
                .isOpr(e.getIsOpr())
                .openTime(e.getOpenTime())
                .closeTime(e.getCloseTime())
                .breakStartTime(e.getBreakStartTime())
                .breakEndTime(e.getBreakEndTime())
                .build();

    }

}
