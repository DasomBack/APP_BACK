package com._thefull.dasom_app_demo.domain.robot.domain;

import com._thefull.dasom_app_demo.domain.dasomLocation.domain.DasomLocation;
import com._thefull.dasom_app_demo.domain.store.domain.Store;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Entity(name = "ROBOT")
public class Robot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROBOT_ID")
    private Long id;

    @Column(name = "MODEL",length = 100)
    private String model;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    @OneToMany(mappedBy = "robot", cascade = CascadeType.ALL)
    @Builder.Default
    private List<DasomLocation> robotLocationCategories = new ArrayList<>();
}

