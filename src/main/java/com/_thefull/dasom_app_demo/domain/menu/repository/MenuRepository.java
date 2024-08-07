package com._thefull.dasom_app_demo.domain.menu.repository;

import com._thefull.dasom_app_demo.domain.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
