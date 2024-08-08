package com._thefull.dasom_app_demo.domain.promotion.service;

import com._thefull.dasom_app_demo.domain.promotion.domain.Status;
import com._thefull.dasom_app_demo.domain.promotion.domain.dto.*;
import com._thefull.dasom_app_demo.global.auth.LoginUser;
import com._thefull.dasom_app_demo.global.exception.AppException;
import com._thefull.dasom_app_demo.global.exception.ErrorCode;
import com._thefull.dasom_app_demo.domain.menu.domain.Category;
import com._thefull.dasom_app_demo.domain.menu.domain.Menu;
import com._thefull.dasom_app_demo.domain.menu.repository.MenuRepository;
import com._thefull.dasom_app_demo.domain.promotion.domain.MenuPromotion;
import com._thefull.dasom_app_demo.domain.promotion.repository.MenuPromotionRepository;
import com._thefull.dasom_app_demo.domain.store.domain.Store;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        List<MenuPromotion> menuPromotionList = menuPromotionRepository.findByStoreAndCategoryOrderByCreateAtDesc(store,category);
        List<SimpleMenuPromoResponseDTO> dtoList = SimpleMenuPromoResponseDTO.toDTOList(menuPromotionList);

        return MenuPromotionListResponseDTO.of(category,Status.ALL,dtoList);
    }

    public MenuPromotionListResponseDTO findMenuPromoListByCategoryAndStatus(Store store, String categoryName, String statusName) {
        Category category = Category.fromCategoryName(categoryName);
        Status status = Status.fromStatusName(statusName);

        List<MenuPromotion> menuPromotionList;

        if(statusName.equals("ALL")){
            menuPromotionList = menuPromotionRepository.findByStoreAndCategoryOrderByCreateAtDesc(store,category);
        }
        else{
            menuPromotionList = menuPromotionRepository.findByStoreAndCategoryAndStatusOrderByCreateAtDesc(store, category, status);
        }

        List<SimpleMenuPromoResponseDTO> dtoList = SimpleMenuPromoResponseDTO.toDTOList(menuPromotionList);

        return MenuPromotionListResponseDTO.of(category,status,dtoList);


    }


    public MenuPromotionResponseDTO updateMenuPromotion(LoginUser user, Long id, UpdateMenuPromotionRequestDTO dto) {
        MenuPromotion menuPromotion = menuPromotionRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_MENU_PROMOTION, "제품 홍보를 찾을 수 없습니다"));
        Menu menu = menuRepository.findById(dto.getMenuId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_MENU, "메뉴를 찾을 수 없습니다"));

        if(isValidUserForMenuPromotion(menuPromotion,user)){
            menuPromotion.updateMenuPromotion(dto,menu);
            MenuPromotion saved = menuPromotionRepository.save(menuPromotion);
            return MenuPromotionResponseDTO.from(saved);

        }else throw new AppException(ErrorCode.UNAUTHORIZED_USER,"ID="+id+" 인 제품 홍보에 대한 수정 권한이 없습니다");

    }

    public MenuPromotionResponseDTO updateStatusOfMenuPromotion(LoginUser user, Long promotionId, String statusName) {
        MenuPromotion promotion = menuPromotionRepository.findById(promotionId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_MENU_PROMOTION, "제품 홍보를 찾을 수 없습니다"));
        Status status = Status.fromStatusName(statusName);
        if (isValidUserForMenuPromotion(promotion,user)){
            if (isValidUpdateStatus(promotion,status)){
                promotion.updateStatus(status);
                MenuPromotion saved = menuPromotionRepository.save(promotion);
                return MenuPromotionResponseDTO.from(saved);
            }else throw new AppException(ErrorCode.UNMATCHED_TIME_STATUS,"ID="+promotionId+" 인 제품 홍보 시간과 "+statusName+"이 맞지 않습니다");

        }
        else throw new AppException(ErrorCode.UNAUTHORIZED_USER,"ID="+promotionId+" 인 제품 홍보에 대한 수정 권한이 없습니다");

    }

    public void deleteMenuPromotion(LoginUser user,Long id) {
        MenuPromotion promotion = menuPromotionRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_MENU_PROMOTION, "제품 홍보를 찾을 수 없습니다"));

        if(isValidUserForMenuPromotion(promotion,user)) menuPromotionRepository.deleteById(id);
        else throw new AppException(ErrorCode.UNAUTHORIZED_USER,"ID="+id+" 인 제품 홍보에 대한 삭제 권한이 없습니다");


    }

    public MenuPromotionResponseDTO findById(LoginUser user, Long id) {
        MenuPromotion menuPromotion = menuPromotionRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_MENU_PROMOTION, "ID="+id+" 인 제품 홍보를 찾을 수 없습니다"));

        if (isValidUserForMenuPromotion(menuPromotion, user))
            return MenuPromotionResponseDTO.from(menuPromotion);
        else throw new AppException(ErrorCode.UNAUTHORIZED_USER,"ID="+id+" 인 제품 홍보에 대한 조회 권한이 없습니다");

    }

    private boolean isValidUserForMenuPromotion(MenuPromotion promotion, LoginUser user){
        return promotion.getStore().getId() == user.getStore().getId();
    }

    private boolean isValidUpdateStatus(MenuPromotion promotion, Status status){
        /* 시작 날짜가 현재 날짜보다 뒤에 있는데 진행으로 바꾸려는 경우 */
        boolean result= !promotion.getPromoStartDate().isAfter(LocalDate.now()) || status != Status.IN_PROGRESS;

        /* 종료 날짜가 지났는데 진행 혹은 예정으로 바꾸려는 경우 */
        if (promotion.getPromoEndDate().isBefore(LocalDate.now())
                && (status==Status.SCHEDULED || status==Status.IN_PROGRESS))
            result=false;

        return result;
    }


}
