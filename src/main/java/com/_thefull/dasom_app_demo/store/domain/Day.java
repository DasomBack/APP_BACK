package com._thefull.dasom_app_demo.store.domain;

import com._thefull.dasom_app_demo.global.exception.AppException;
import com._thefull.dasom_app_demo.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Day {
    MON(1,"월요일"),
    TUE(2, "화요일"),
    WEN(3, "수요일"),
    TUR(4, "목요일"),
    FRI(5, "금요일"),
    SAT(6,"토요일"),
    SUN(7,"일요일");

    private final int intVal;
    private final String koreanName;

    public static Day fromIntVal(int intVal){
        for(Day day: Day.values()){
            if (day.getIntVal()==intVal)
                return day;
        }
        throw new AppException(ErrorCode.INVALID_DAY,"유효하지 않은 요일입니다");
    }


}
