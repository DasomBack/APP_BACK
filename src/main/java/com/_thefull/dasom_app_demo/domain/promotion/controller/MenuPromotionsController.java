package com._thefull.dasom_app_demo.domain.promotion.controller;

import com._thefull.dasom_app_demo.domain.promotion.domain.dto.UpdateMenuPromotionRequestDTO;
import com._thefull.dasom_app_demo.global.auth.AuthUser;
import com._thefull.dasom_app_demo.global.auth.LoginUser;
import com._thefull.dasom_app_demo.domain.promotion.domain.dto.MenuPromotionRequestDTO;
import com._thefull.dasom_app_demo.domain.promotion.domain.dto.MenuPromotionResponseDTO;
import com._thefull.dasom_app_demo.domain.promotion.domain.dto.MenuPromotionListResponseDTO;
import com._thefull.dasom_app_demo.domain.promotion.service.MenuPromotionService;
import com._thefull.dasom_app_demo.domain.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/* 제품할인 컨트롤러 */
@RequestMapping("/menu-promo")
@RestController
@RequiredArgsConstructor
public class MenuPromotionsController {

    private final MenuPromotionService menuPromotionService;

    /* 제품할인 생성 */
    @PostMapping
    public ResponseEntity<MenuPromotionResponseDTO> createMenuPromotion(@AuthUser LoginUser user,
                                                                        @RequestBody final MenuPromotionRequestDTO dto){

        Store store = user.getStore();
        MenuPromotionResponseDTO responseDTO = menuPromotionService.createMenuPromotion(dto,store);

        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<MenuPromotionResponseDTO> findMenuPromotion(@AuthUser LoginUser user,
                                                                      @RequestParam(name = "id")Long id){
        MenuPromotionResponseDTO response = menuPromotionService.findById(user,id);

        return ResponseEntity.ok().body(response);

    }

    /* 카테고리별 '전체' 상태 조회 */
    @GetMapping("/category")
    public ResponseEntity<MenuPromotionListResponseDTO> findAllMenuPromoListByCategory(@AuthUser LoginUser user,
                                                                                       @RequestParam(name = "category")String category){

        Store store = user.getStore();

        MenuPromotionListResponseDTO response = menuPromotionService.findAllMenuPromoListByCategory(store, category);

        return ResponseEntity.ok().body(response);
    }

    /* 카테고리 & 상태별 조회 */
    @GetMapping("/category/status")
    public ResponseEntity<MenuPromotionListResponseDTO> findMenuPromoListByCategoryAndStatus(@AuthUser LoginUser user,
                                                                                             @RequestParam(name = "category")String category,
                                                                                             @RequestParam(name = "status")String status){


        Store store = user.getStore();
        MenuPromotionListResponseDTO response=menuPromotionService.findMenuPromoListByCategoryAndStatus(store,category,status);

        return ResponseEntity.ok().body(response);

    }

    @PatchMapping("/status")
    public ResponseEntity<MenuPromotionResponseDTO> updateStatusOfMenuPromotion(@AuthUser LoginUser user,
                                                                                @RequestParam(name = "id")Long id,
                                                                                @RequestBody String status){
        MenuPromotionResponseDTO response =menuPromotionService.updateStatusOfMenuPromotion(user,id,status);
        return ResponseEntity.ok().body(response);

    }

    @PutMapping
    public ResponseEntity<MenuPromotionResponseDTO> updateMenuPromotion(@AuthUser LoginUser user,
                                                                        @RequestParam(name = "id")Long id,
                                                                        @RequestBody final UpdateMenuPromotionRequestDTO requestDTO){
        MenuPromotionResponseDTO responseDTO = menuPromotionService.updateMenuPromotion(user,id,requestDTO);

        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteMenuPromotion(@AuthUser LoginUser user,
                                                      @RequestParam(name = "id")Long id){
        menuPromotionService.deleteMenuPromotion(user,id);
        String response= "ID="+id+"가 정상적으로 삭제되었습니다.";

        return ResponseEntity.ok().body(response);
    }






}
