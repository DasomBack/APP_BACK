package com._thefull.dasom_app_demo.store.service;

import com._thefull.dasom_app_demo.global.exception.AppException;
import com._thefull.dasom_app_demo.global.exception.ErrorCode;
import com._thefull.dasom_app_demo.store.domain.Day;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DayConverter implements AttributeConverter<Day, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Day day) {
        if(day==null)
            throw new AppException(ErrorCode.INVALID_DAY,"유효하지 않은 요일입니다");
        return day.getIntVal();

    }

    @Override
    public Day convertToEntityAttribute(Integer intVal) {
        if(intVal==null)
            throw new AppException(ErrorCode.INVALID_DAY,"유효하지 않은 요일입니다");
        return Day.fromIntVal(intVal);
    }
}
