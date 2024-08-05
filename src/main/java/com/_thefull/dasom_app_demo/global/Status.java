package com._thefull.dasom_app_demo.global;

import com._thefull.dasom_app_demo.global.exception.AppException;
import com._thefull.dasom_app_demo.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public enum Status {
    IN_PROGRESS(1, "진행중"),
    SCHEDULED(2,"예정"),
    STOPPED(3,"중지"),
    COMPLETED(4,"완료"),
    ALL(5,"전체");

    private final int stateNum;
    private final String stateType;

    public static Status fromStatusNum(int stateNum){
        for (Status s: Status.values()){
            if (s.getStateNum()==stateNum)
                return s;
        }
        throw new AppException(ErrorCode.INVALID_STATE,"확인되지 않은 상태입니다");
    }

    public static Status fromStatusType(String stateType){
        for (Status s: Status.values()){
            if(s.getStateType().equals(stateType)){
                return s;
            }
        }
        throw new AppException(ErrorCode.INVALID_STATE,"확인되지 않은 상태입니다");
    }

    public static Status fromStatusName(String statusName){
        for (Status s: Status.values()){
            if (s.name().equals(statusName)){
                return s;
            }
        }
        throw new AppException(ErrorCode.INVALID_STATE,"확인되지 않은 상태입니다");
    }

    public static Status determinStatusFromDate(LocalDate startDate, LocalDate endDate){
        LocalDate now = LocalDate.now();
        if (startDate.isBefore(now))
            return Status.SCHEDULED;
        else if(endDate.isAfter(now))
            return Status.COMPLETED;
        else
            return Status.IN_PROGRESS;

    }
}