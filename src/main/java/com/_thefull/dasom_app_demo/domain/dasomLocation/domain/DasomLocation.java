package com._thefull.dasom_app_demo.domain.dasomLocation.domain;

import com._thefull.dasom_app_demo.domain.dasomLocation.domain.dto.DasomLocationRequestDTO;
import com._thefull.dasom_app_demo.domain.dasomLocation.service.StringListConverter;
import com._thefull.dasom_app_demo.global.BaseEntity;
import com._thefull.dasom_app_demo.domain.robot.domain.Robot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/*
*  -- 카페봇 위치설정 객체 --
* DASOM_LOCATION 이름의 RDS 테이블과 직접적으로 매핑되는 엔티티 객체입니다.
* 생성일과 수정일을 포함하는 BaseEntity를 상속받고 있으며,
* ID는 DB 자동 생성, 다솜 위치와 앞/좌/우/오른쪽 앞/왼쪽 앞 에 대한 STRING 정보를 LIST로 변환하여 저장하고 있습니다.
* USE 필드는 해당 위치설정의 사용 여부를 나타냅니다.
* */
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
    @Convert(converter = StringListConverter.class)
    private List<String> leftSide;

    @Column(name = "LEFT_FRONT")
    @Convert(converter = StringListConverter.class)
    private List<String> leftFront;

    @Column(name = "FRONT")
    @Convert(converter = StringListConverter.class)
    private List<String> front;

    @Column(name = "RIGHT_SIDE")  // 변경된 필드명
    @Convert(converter = StringListConverter.class)
    private List<String> rightSide;

    @Column(name = "RIGHT_FRONT")
    @Convert(converter = StringListConverter.class)
    private List<String> rightFront;

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
        this.front=dto.getFront();
        this.location= dto.getLocation();

    }
}
