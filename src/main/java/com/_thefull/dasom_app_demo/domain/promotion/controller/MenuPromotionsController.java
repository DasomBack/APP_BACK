package com._thefull.dasom_app_demo.domain.promotion.controller;

import com._thefull.dasom_app_demo.global.exception.AppException;
import com._thefull.dasom_app_demo.global.exception.ErrorCode;
import com._thefull.dasom_app_demo.domain.menu.service.MenuService;
import com._thefull.dasom_app_demo.domain.promotion.domain.dto.MenuPromotionRequestDTO;
import com._thefull.dasom_app_demo.domain.promotion.domain.dto.MenuPromotionResponseDTO;
import com._thefull.dasom_app_demo.domain.promotion.domain.dto.MenuPromotionListResponseDTO;
import com._thefull.dasom_app_demo.domain.promotion.service.MenuPromotionService;
import com._thefull.dasom_app_demo.domain.store.domain.Store;
import com._thefull.dasom_app_demo.domain.store.repository.StoreRepository;
import com._thefull.dasom_app_demo.domain.store.service.StoreService;
import com._thefull.dasom_app_demo.domain.user.domain.User;
import com._thefull.dasom_app_demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/* 제품할인 컨트롤러 */
@RequestMapping("/menu-promo")
@RestController
@RequiredArgsConstructor
public class MenuPromotionsController {

    private final MenuPromotionService menuPromotionService;
    private final StoreService storeService;
    private final MenuService menuService;

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
    @PostMapping
    public ResponseEntity<MenuPromotionResponseDTO> createMenuPromotion(@RequestBody final MenuPromotionRequestDTO dto){
        Store store = defaultTestStore();
        MenuPromotionResponseDTO responseDTO = menuPromotionService.createMenuPromotion(dto,store);

        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<MenuPromotionResponseDTO> findMenuPromotion(@RequestParam(name = "id")Long id){
        MenuPromotionResponseDTO response = menuPromotionService.findById(id);

        return ResponseEntity.ok().body(response);

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

    @PutMapping
    public ResponseEntity<MenuPromotionResponseDTO> updateMenuPromotion(@RequestParam(name = "id")Long id,
                                                                        @RequestBody final MenuPromotionRequestDTO requestDTO){
        MenuPromotionResponseDTO responseDTO = menuPromotionService.updateMenuPromotion(id,requestDTO);

        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteMenuPromotion(@RequestParam(name = "id")Long id){
        menuPromotionService.deleteMenuPromotion(id);
        String response= "ID="+id+"가 정상적으로 삭제되었습니다.";

        return ResponseEntity.ok().body(response);
    }






}
