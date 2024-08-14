package com._thefull.dasom_app_demo.domain.store.domain;

import com._thefull.dasom_app_demo.domain.store.service.DayConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Table(name = "STORE_OPERATING_HOURS")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreOperatingHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 7 1 2 3 4 5 6
    @Column(columnDefinition = "TINYINT")
    @NotNull @Convert(converter = DayConverter.class)
    private Day day;

    @Column(name = "is_opr")
    private Boolean isOpr;

    @Column(name = "open_time")
    private LocalTime openTime;

    @Column(name = "close_time")
    private LocalTime closeTime;

    @Column(name = "break_start_time")
    private LocalTime breakStartTime;

    @Column(name = "break_end_time")
    private LocalTime breakEndTime;

    @JoinColumn(name = "store_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Store store;


}
