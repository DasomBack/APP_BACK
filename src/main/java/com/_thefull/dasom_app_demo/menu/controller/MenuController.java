package com._thefull.dasom_app_demo.menu.controller;

import com._thefull.dasom_app_demo.menu.service.MenuService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;


}
