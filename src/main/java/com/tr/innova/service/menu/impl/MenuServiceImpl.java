package com.tr.innova.service.menu.impl;

import com.tr.innova.dto.menu.MenuDto;
import com.tr.innova.dto.menu.MenuModelDto;
import com.tr.innova.common.security.service.SessionInfo;
import com.tr.innova.service.menu.MenuService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    private final SessionInfo sessionInfo;
    private final MenuDto menuDto;

    public MenuServiceImpl(SessionInfo sessionInfo, MenuDto menuDto) {
        this.sessionInfo = sessionInfo;
        this.menuDto = menuDto;
    }

    @Override
    public MenuDto filterMenuByPermissions() {

        List<String> permissions = sessionInfo.getPermissions();

        MenuDto filteredMenu = new MenuDto();

        List<MenuModelDto> filteredModelList = menuDto.getModel().stream()
                .filter(menu -> hasAccess(menu.getPermissions(), permissions))
                .map(menu -> {
                    menu.setItems(filterItems(menu.getItems(), permissions));
                    return menu;
                })
                .collect(Collectors.toList());

        filteredMenu.setModel(filteredModelList);

        return filteredMenu;
    }

    private boolean hasAccess(List<String> requiredPermissions, List<String> userPermissions) {
        if (requiredPermissions == null || requiredPermissions.isEmpty()) {
            return true;
        }
        return userPermissions.stream().anyMatch(requiredPermissions::contains);
    }

    private List<MenuModelDto> filterItems(List<MenuModelDto> items, List<String> userPermissions) {
        if (items == null || items.isEmpty()) return null;

        return items.stream()
                .filter(item -> hasAccess(item.getPermissions(), userPermissions))
                .map(item -> {
                    item.setItems(filterItems(item.getItems(), userPermissions));
                    return item;
                })
                .collect(Collectors.toList());
    }
}
