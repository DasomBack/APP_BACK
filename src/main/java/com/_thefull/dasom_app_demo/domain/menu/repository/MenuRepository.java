package com._thefull.dasom_app_demo.domain.menu.repository;

import com._thefull.dasom_app_demo.domain.menu.domain.Menu;
import com._thefull.dasom_app_demo.domain.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findAllByStore(Store store);

    List<Menu> findAllByStoreAndNameContaining(Store store, String search);
}
