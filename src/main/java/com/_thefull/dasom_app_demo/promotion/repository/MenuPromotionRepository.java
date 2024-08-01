package com._thefull.dasom_app_demo.promotion.repository;

import com._thefull.dasom_app_demo.global.Status;
import com._thefull.dasom_app_demo.menu.domain.Category;
import com._thefull.dasom_app_demo.promotion.domain.MenuPromotion;
import com._thefull.dasom_app_demo.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuPromotionRepository extends JpaRepository<MenuPromotion, Long> {

    List<MenuPromotion> findByStore(Store store);

    List<MenuPromotion> findByStoreAndCategory(Store store, Category category);

    List<MenuPromotion> findByStoreAndCategoryAndStatus(Store store, Category category, Status status);


}
