package com._thefull.dasom_app_demo.domain.promotion.domain;

import com._thefull.dasom_app_demo.global.exception.AppException;
import com._thefull.dasom_app_demo.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DateType {
    ALWAYS(1, "상시"),
    TWO_WEEKS(2, "2주"),
    ONE_WEEK(3,"1주"),
    OTHER(4,"직접입력");

    private final int intVal;
    private final String koreanName;

    public static DateType fromIntVal(int intVal){
        for(DateType dateType: DateType.values()){
            if(dateType.intVal==intVal)
                return dateType;
        }
        throw new AppException(ErrorCode.NO_DATE_TYPE,"존재하지 않는 기간 종류입니다");
    }

    public static DateType fromName(String name){
        for(DateType dateType: DateType.values()){
            if(dateType.name().equals(name))
                return dateType;
        }
        throw new AppException(ErrorCode.NO_DATE_TYPE,"존재하지 않는 기간 종류입니다");
    }

}
