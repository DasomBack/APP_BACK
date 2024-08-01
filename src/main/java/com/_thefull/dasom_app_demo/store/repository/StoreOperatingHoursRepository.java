package com._thefull.dasom_app_demo.store.repository;

import com._thefull.dasom_app_demo.store.domain.StoreOperatingHours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreOperatingHoursRepository extends JpaRepository<StoreOperatingHours, Long> {
}
