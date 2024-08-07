package com._thefull.dasom_app_demo.domain.promotion.service;

import com._thefull.dasom_app_demo.domain.promotion.domain.Status;
import com._thefull.dasom_app_demo.global.exception.AppException;
import com._thefull.dasom_app_demo.global.exception.ErrorCode;
import com._thefull.dasom_app_demo.domain.menu.domain.Category;
import com._thefull.dasom_app_demo.domain.menu.domain.Menu;
import com._thefull.dasom_app_demo.domain.menu.repository.MenuRepository;
import com._thefull.dasom_app_demo.domain.promotion.domain.MenuPromotion;
import com._thefull.dasom_app_demo.domain.promotion.domain.dto.MenuPromotionRequestDTO;
import com._thefull.dasom_app_demo.domain.promotion.domain.dto.MenuPromotionResponseDTO;
import com._thefull.dasom_app_demo.domain.promotion.domain.dto.MenuPromotionListResponseDTO;
import com._thefull.dasom_app_demo.domain.promotion.domain.dto.SimpleMenuPromoResponseDTO;
import com._thefull.dasom_app_demo.domain.promotion.repository.MenuPromotionRepository;
import com._thefull.dasom_app_demo.domain.store.domain.Store;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MenuPromotionService {

    private final MenuPromotionRepository menuPromotionRepository;
    private final MenuRepository menuRepository;

    public MenuPromotionResponseDTO createMenuPromotion(MenuPromotionRequestDTO dto, Store store) {
        Menu menu = menuRepository.findById(dto.getMenuId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_MENU, "메뉴를 찾을 수 없습니다"));

        Status status = Status.determinStatusFromDate(dto.getPromoStartDate(), dto.getPromoEndDate());

        MenuPromotion newPromotion = dto.from(menu, status, store);

        MenuPromotion saved = menuPromotionRepository.save(newPromotion);

        return MenuPromotionResponseDTO.from(saved);

    }


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


    public MenuPromotionResponseDTO updateMenuPromotion(Long id,MenuPromotionRequestDTO dto) {
        MenuPromotion menuPromotion = menuPromotionRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_MENU_PROMOTION, "제품 홍보를 찾을 수 없습니다"));
        Menu menu = menuRepository.findById(dto.getMenuId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_MENU, "메뉴를 찾을 수 없습니다"));

        menuPromotion.updateMenuPromotion(dto,menu);

        MenuPromotion saved = menuPromotionRepository.save(menuPromotion);
        return MenuPromotionResponseDTO.from(saved);
    }

    public void deleteMenuPromotion(Long id) {
        menuPromotionRepository.deleteById(id);
    }

    public MenuPromotionResponseDTO findById(Long id) {
        MenuPromotion menuPromotion = menuPromotionRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_MENU_PROMOTION, "제품 홍보를 찾을 수 없습니다"));
        return MenuPromotionResponseDTO.from(menuPromotion);
    }
}
