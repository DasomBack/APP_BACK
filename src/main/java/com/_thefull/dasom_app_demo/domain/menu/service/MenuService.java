package com._thefull.dasom_app_demo.domain.menu.service;

import com._thefull.dasom_app_demo.domain.menu.domain.Menu;
import com._thefull.dasom_app_demo.domain.menu.domain.dto.MenuResponseDTO;
import com._thefull.dasom_app_demo.domain.menu.domain.dto.SimpleMenuResponseDTO;
import com._thefull.dasom_app_demo.domain.menu.repository.MenuRepository;
import com._thefull.dasom_app_demo.domain.store.domain.Store;
import com._thefull.dasom_app_demo.global.exception.AppException;
import com._thefull.dasom_app_demo.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public MenuResponseDTO getMenuById(Store store, Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_MENU, "메뉴를 찾을 수 없습니다"));
        if (isValidStoreToMenu(store,menu))
            return MenuResponseDTO.of(menu);
        else throw new AppException(ErrorCode.UNAUTHORIZED_USER, "메뉴를 찾을 수 없습니다");
    }

    public List<SimpleMenuResponseDTO> findAllByStore(Store store) {
        List<Menu> allByStore = menuRepository.findAllByStore(store);
        return allByStore.stream().map(SimpleMenuResponseDTO::of).collect(Collectors.toList());
    }

    public List<SimpleMenuResponseDTO> findBySearchAndStore(String search, Store store) {
        List<Menu> searchedMenuList = menuRepository.findAllByStoreAndNameContaining(store, search);
        return searchedMenuList.stream().map(SimpleMenuResponseDTO::of).collect(Collectors.toList());
    }

    private boolean isValidStoreToMenu(Store store, Menu menu){
        return store.getId()==menu.getStore().getId();
    }


}
