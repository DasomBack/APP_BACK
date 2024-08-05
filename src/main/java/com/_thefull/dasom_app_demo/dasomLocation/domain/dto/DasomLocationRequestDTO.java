package com._thefull.dasom_app_demo.dasomLocation.domain.dto;

import com._thefull.dasom_app_demo.dasomLocation.domain.DasomLocation;
import com._thefull.dasom_app_demo.robot.domain.Robot;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DasomLocationRequestDTO {

    @NotBlank
    private String location;

    private String leftSide;
    private String rightSide;
    private String leftFront;
    private String rightFront;
    private String front;

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
