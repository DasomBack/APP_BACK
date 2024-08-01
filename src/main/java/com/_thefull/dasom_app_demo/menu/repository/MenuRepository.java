package com._thefull.dasom_app_demo.menu.repository;

import com._thefull.dasom_app_demo.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
