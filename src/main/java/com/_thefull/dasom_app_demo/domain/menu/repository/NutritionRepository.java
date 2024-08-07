package com._thefull.dasom_app_demo.domain.menu.repository;

import com._thefull.dasom_app_demo.domain.menu.domain.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutritionRepository extends JpaRepository<Nutrition, Long> {
}
