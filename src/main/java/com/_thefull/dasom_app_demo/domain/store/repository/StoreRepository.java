package com._thefull.dasom_app_demo.domain.store.repository;

import com._thefull.dasom_app_demo.domain.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findByCode(String code);

}
