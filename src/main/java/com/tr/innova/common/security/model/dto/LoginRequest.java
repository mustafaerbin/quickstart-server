package com.tr.innova.common.security.model.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;
}
