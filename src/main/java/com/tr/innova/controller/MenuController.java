package com.tr.innova.controller;

import com.tr.innova.common.utils.AppResponse;
import com.tr.innova.common.utils.ResponseUtil;
import com.tr.innova.dto.menu.MenuDto;
import com.tr.innova.service.menu.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/all")
    public ResponseEntity<AppResponse<MenuDto>> getMenu() {
        return ResponseUtil.wrapDataResponse(menuService.filterMenuByPermissions());
    }

}
