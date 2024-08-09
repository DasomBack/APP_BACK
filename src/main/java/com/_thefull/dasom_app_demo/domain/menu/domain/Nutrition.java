package com._thefull.dasom_app_demo.domain.menu.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


// 메뉴 영양 성분표
@Entity
@Table(name = "NUTRITIONS")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Nutrition {

    @Id
    @Column(name = "NUTRI_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 칼로리
    private Integer cal;

    // 당류
    private Integer sugars;

    // 단백질
    private Integer protein;

    // 나트륨
    private Integer sodium;

    // 포화 지방
    private Integer fat;

    // 카페인
    private Integer caffeine;

    // 영양 성분표 이미지 url
    @Column(name = "img_url")
    private String imgUrl;

    @JoinColumn(name = "menu_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Menu menu;




}
