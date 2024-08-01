package com._thefull.dasom_app_demo.promotion.controller;

import com._thefull.dasom_app_demo.global.exception.AppException;
import com._thefull.dasom_app_demo.global.exception.ErrorCode;
import com._thefull.dasom_app_demo.promotion.domain.dto.CreateMenuPromotionRequestDTO;
import com._thefull.dasom_app_demo.promotion.domain.dto.CreateMenuPromotionResponseDTO;
import com._thefull.dasom_app_demo.promotion.domain.dto.MenuPromotionListResponseDTO;
import com._thefull.dasom_app_demo.promotion.domain.dto.SimpleMenuPromoResponseDTO;
import com._thefull.dasom_app_demo.promotion.service.MenuPromotionService;
import com._thefull.dasom_app_demo.store.domain.Store;
import com._thefull.dasom_app_demo.store.repository.StoreRepository;
import com._thefull.dasom_app_demo.store.service.StoreService;
import com._thefull.dasom_app_demo.user.domain.User;
import com._thefull.dasom_app_demo.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* 제품할인 컨트롤러 */
@RequestMapping("/menu-promo")
@RestController
@RequiredArgsConstructor
public class MenuPromotionsController {

    private final MenuPromotionService menuPromotionService;
    private final StoreService storeService;

    /****************** 로그인 개발하기 전 테스트용 ***********************/
    private final UserRepository userRepository;
    private User defaultTestUser(){
        User user = userRepository.findById(1l)
                .orElseThrow(() -> new AppException(ErrorCode.NO_CATEGORY, ""));

        return user;
    }

    private final StoreRepository storeRepository;
    private Store defaultTestStore(){
        Store store = storeRepository.findById(1l)
                .orElseThrow(() -> new AppException(ErrorCode.NO_CATEGORY, ""));

        return store;
    }

    /*****************************************************************/


    /* 제품할인 생성 */
    @PostMapping("/menupromotion")
    public ResponseEntity createMenuPromotion(@Valid final CreateMenuPromotionRequestDTO dto){
        // CreateMenuPromotionResponseDTO responseDTO = menuPromotionService.create(dto);


        return ResponseEntity.ok().body("");
    }

    /* 카테고리별 전체 상태 조회 */
    @GetMapping("/category")
    public ResponseEntity<MenuPromotionListResponseDTO> findAllMenuPromoListByCategory(@RequestParam(name = "category")String category){

        Store store = storeService.findById(1l);

        MenuPromotionListResponseDTO response = menuPromotionService.findAllMenuPromoListByCategory(store, category);

        return ResponseEntity.ok().body(response);
    }

    /* 카테고리 & 상태별 조회 */
    @GetMapping("/category/status")
    public ResponseEntity<MenuPromotionListResponseDTO> findMenuPromoListByCategoryAndStatus(@RequestParam(name = "category")String category,
                                                                                                 @RequestParam(name = "status")String status){

        Store store = storeService.findById(1l);
        MenuPromotionListResponseDTO response=menuPromotionService.findMenuPromoListByCategoryAndStatus(store,category,status);

        return ResponseEntity.ok().body(response);

    }


}
