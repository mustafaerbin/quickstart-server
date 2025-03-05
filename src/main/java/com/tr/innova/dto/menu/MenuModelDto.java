package com.tr.innova.dto.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class MenuModelDto {

    private String label;
    private String icon;
    private List<String> routerLink;
    private List<MenuModelDto> items;
    @JsonIgnore
    private List<String> permissions;
}
