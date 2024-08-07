package com._thefull.dasom_app_demo.domain.dasomLocation.domain.dto;

import com._thefull.dasom_app_demo.domain.dasomLocation.domain.DasomLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Builder
@AllArgsConstructor
public class DasomLocationResponseDTO {

    private Long id;
    private String location;
    private String leftSide;
    private String rightSide;
    private String leftFront;
    private String rightFront;
    private String front;

    private boolean use;

    public static DasomLocationResponseDTO of(DasomLocation e){
        return DasomLocationResponseDTO.builder()
                .id(e.getId())
                .location(e.getLocation())
                .leftSide(e.getLeftSide())
                .rightSide(e.getRightSide())
                .leftFront(e.getLeftFront())
                .rightFront(e.getRightFront())
                .front(e.getFront())
                .use(e.getUse())
                .build();
    }

    public static List<DasomLocationResponseDTO> toDTOList(List<DasomLocation> dasomLocationList){
        return dasomLocationList.stream()
                .map(DasomLocationResponseDTO::of)
                .collect(Collectors.toList());
    }

}
