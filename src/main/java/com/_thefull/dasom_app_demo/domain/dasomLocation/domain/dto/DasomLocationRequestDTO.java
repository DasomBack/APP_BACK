package com._thefull.dasom_app_demo.domain.dasomLocation.domain.dto;

import com._thefull.dasom_app_demo.domain.dasomLocation.domain.DasomLocation;
import com._thefull.dasom_app_demo.domain.robot.domain.Robot;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
*  -- 카페봇 위치설정 request DTO --
*  카페봇 위치 설정 등록 및 변경 시 사용되는 dto입니다.
* */
@Data
@NoArgsConstructor
public class DasomLocationRequestDTO {

    @NotBlank
    private String location;

    private List<String> leftSide;
    private List<String> rightSide;
    private List<String> leftFront;
    private List<String> rightFront;
    private List<String> front;

    public DasomLocation from(Robot robot){
        return DasomLocation.builder()
                .location(this.location)
                .leftSide(this.leftSide)
                .rightSide(this.rightSide)
                .leftFront(this.leftFront)
                .rightFront(this.rightFront)
                .front(this.front)
                .robot(robot)
                .use(true)
                .build();
    }

}
