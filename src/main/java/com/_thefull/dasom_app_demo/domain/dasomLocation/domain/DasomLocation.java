package com._thefull.dasom_app_demo.domain.dasomLocation.domain;

import com._thefull.dasom_app_demo.domain.dasomLocation.domain.dto.DasomLocationRequestDTO;
import com._thefull.dasom_app_demo.global.BaseEntity;
import com._thefull.dasom_app_demo.domain.robot.domain.Robot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity(name = "DASOM_LOCATION")
public class DasomLocation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROBOT_LOCATION_ID",updatable = false)
    private Long id;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "LEFT_SIDE")  // 변경된 필드명
    private String leftSide;

    @Column(name = "LEFT_FRONT")
    private String leftFront;

    @Column(name = "FRONT")
    private String front;

    @Column(name = "RIGHT_SIDE")  // 변경된 필드명
    private String rightSide;

    @Column(name = "RIGHT_FRONT")
    private String rightFront;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROBOT_ID")
    private Robot robot;  // Robot과의 관계 정의

    @Column(name = "`USE`")
    @Builder.Default
    private Boolean use=true;


    public void changeUse(Boolean use){
        this.use=use;
    }

    public void update(DasomLocationRequestDTO dto) {
        this.leftSide=dto.getLeftSide();
        this.rightSide=dto.getRightSide();
        this.leftFront=dto.getLeftFront();
        this.rightFront=dto.getRightFront();
        this.location= dto.getLocation();

    }
}
