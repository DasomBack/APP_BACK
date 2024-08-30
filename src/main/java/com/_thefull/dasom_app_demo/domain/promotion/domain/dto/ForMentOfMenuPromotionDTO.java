package com._thefull.dasom_app_demo.domain.promotion.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ForMentOfMenuPromotionDTO {
    private Long menuId;
    private String discType;
    private Integer discVal;
    private String dateType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isEqlOprTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime endTime;
    private Boolean isEqlPromoTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime mentStartTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime mentEndTime;
    private Integer mentInterval;
    private Boolean isAddDiscCond;
    private String addDiscCond;
    private Boolean isAddMenuDesc;
    private String addMenuDesc;

}
