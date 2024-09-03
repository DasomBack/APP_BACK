package com._thefull.dasom_app_demo.domain.menu.controller;

import com._thefull.dasom_app_demo.domain.menu.domain.dto.MenuResponseDTO;
import com._thefull.dasom_app_demo.domain.menu.domain.dto.SimpleMenuResponseDTO;
import com._thefull.dasom_app_demo.domain.menu.service.MenuService;
import com._thefull.dasom_app_demo.domain.store.domain.Store;
import com._thefull.dasom_app_demo.domain.store.service.StoreService;
import com._thefull.dasom_app_demo.global.auth.AuthUser;
import com._thefull.dasom_app_demo.global.auth.LoginUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;
    private final StoreService storeService;

    @GetMapping("/all")
    public ResponseEntity<List<SimpleMenuResponseDTO>> findAllMenuList(@AuthUser LoginUser user){
        Store store = user.getStore();
        List<SimpleMenuResponseDTO> response = menuService.findAllByStore(store);

        return ResponseEntity.ok().body(response);

    }



    @GetMapping("/search")
    public ResponseEntity<List<SimpleMenuResponseDTO>> findSearchMenuList(@AuthUser LoginUser user,
                                                                          @RequestParam(name = "search")String search){
        Store store = user.getStore();
        List<SimpleMenuResponseDTO> response = menuService.findBySearchAndStore(search,store);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<MenuResponseDTO> getMenuDetails(@AuthUser LoginUser user,
                                                          @RequestParam(name = "id")Long id){
        Store store = user.getStore();
        MenuResponseDTO response = menuService.getMenuById(store, id);

        return ResponseEntity.ok().body(response);
    }

}
