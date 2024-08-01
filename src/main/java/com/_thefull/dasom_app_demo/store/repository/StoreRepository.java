package com._thefull.dasom_app_demo.store.repository;

import com._thefull.dasom_app_demo.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
