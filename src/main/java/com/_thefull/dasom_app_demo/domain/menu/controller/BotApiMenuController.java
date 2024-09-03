package com._thefull.dasom_app_demo.domain.menu.controller;


import com._thefull.dasom_app_demo.domain.menu.domain.dto.SimpleMenuResponseDTO;
import com._thefull.dasom_app_demo.domain.menu.service.MenuService;
import com._thefull.dasom_app_demo.domain.store.domain.Store;
import com._thefull.dasom_app_demo.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bot/menu")
public class BotApiMenuController {
    private final MenuService menuService;
    private final StoreService storeService;

    @GetMapping
    public ResponseEntity<List<SimpleMenuResponseDTO>> findMenuListByCategory(@RequestParam(name = "storeCode") String storeCode,
                                                                              @RequestParam(name = "category")String category){
        Store store= storeService.findByStoreCode(storeCode);

        List<SimpleMenuResponseDTO> response =menuService.findByCategory(store, category);

        return ResponseEntity.ok().body(response);

    }

}
