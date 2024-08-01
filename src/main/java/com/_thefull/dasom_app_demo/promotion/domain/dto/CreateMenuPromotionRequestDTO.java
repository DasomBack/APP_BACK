package com._thefull.dasom_app_demo.promotion.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class CreateMenuPromotionRequestDTO {

    private String category;
    private String menu;

    private int price;
    private boolean boolIsDisc;
    private boolean boolIsRate;
    private int discVal;
    private int discRate;
    private int discPrice;

    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean boolIsAlways;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime mentStartTime;
    private LocalTime mentEndTime;
    private int intervalMinutes;

    private boolean boolAddCond;
    private String addDiscCond;

    private boolean boolAddDesc;
    private String addMenuDesc;

    private String ment;


}
