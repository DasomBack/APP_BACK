package com._thefull.dasom_app_demo.promotion.service;

import com._thefull.dasom_app_demo.global.Status;
import com._thefull.dasom_app_demo.menu.domain.Category;
import com._thefull.dasom_app_demo.promotion.domain.MenuPromotion;
import com._thefull.dasom_app_demo.promotion.domain.dto.CreateMenuPromotionRequestDTO;
import com._thefull.dasom_app_demo.promotion.domain.dto.CreateMenuPromotionResponseDTO;
import com._thefull.dasom_app_demo.promotion.domain.dto.MenuPromotionListResponseDTO;
import com._thefull.dasom_app_demo.promotion.domain.dto.SimpleMenuPromoResponseDTO;
import com._thefull.dasom_app_demo.promotion.repository.MenuPromotionRepository;
import com._thefull.dasom_app_demo.store.domain.Store;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuPromotionService {

    private final MenuPromotionRepository menuPromotionRepository;


    public MenuPromotionListResponseDTO findAllMenuPromoListByCategory(Store store, String categoryname) {
        Category category = Category.fromCategoryName(categoryname);
        List<MenuPromotion> menuPromotionList = menuPromotionRepository.findByStoreAndCategory(store,category);
        List<SimpleMenuPromoResponseDTO> dtoList = SimpleMenuPromoResponseDTO.toDTOList(menuPromotionList);

        return MenuPromotionListResponseDTO.of(category,Status.ALL,dtoList);
    }

    public MenuPromotionListResponseDTO findMenuPromoListByCategoryAndStatus(Store store, String categoryName, String statusName) {
        Category category = Category.fromCategoryName(categoryName);
        Status status = Status.fromStatusName(statusName);

        List<MenuPromotion> menuPromotionList=new ArrayList<>();

        if(statusName.equals("ALL")){
            menuPromotionList = menuPromotionRepository.findByStoreAndCategory(store,category);
        }
        else{
            menuPromotionList = menuPromotionRepository.findByStoreAndCategoryAndStatus(store, category, status);
        }

        List<SimpleMenuPromoResponseDTO> dtoList = SimpleMenuPromoResponseDTO.toDTOList(menuPromotionList);

        return MenuPromotionListResponseDTO.of(category,status,dtoList);


    }

//    public CreateMenuPromotionResponseDTO create(CreateMenuPromotionRequestDTO dto) {
//
//
//    }


}
