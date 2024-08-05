package com._thefull.dasom_app_demo.menu.repository;

import com._thefull.dasom_app_demo.menu.domain.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutritionRepository extends JpaRepository<Nutrition, Long> {
}
