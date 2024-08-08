package com._thefull.dasom_app_demo.domain.promotion.repository;

import com._thefull.dasom_app_demo.domain.promotion.domain.Status;
import com._thefull.dasom_app_demo.domain.menu.domain.Category;
import com._thefull.dasom_app_demo.domain.promotion.domain.MenuPromotion;
import com._thefull.dasom_app_demo.domain.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuPromotionRepository extends JpaRepository<MenuPromotion, Long> {

    List<MenuPromotion> findByStore(Store store);

    List<MenuPromotion> findByStoreAndCategoryOrderByCreateAtDesc(Store store, Category category);

    List<MenuPromotion> findByStoreAndCategoryAndStatusOrderByCreateAtDesc(Store store, Category category, Status status);


}
