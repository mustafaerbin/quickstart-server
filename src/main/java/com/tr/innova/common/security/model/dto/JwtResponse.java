package com.tr.innova.common.security.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String username;
    private List<String> roles;
    private List<String> grants;

    // Constructor
    public JwtResponse(String token, String username, List<String> roles, List<String> grants) {
        this.token = token;
        this.username = username;
        this.roles = roles;
        this.grants = grants;
    }

    public JwtResponse(String token) {
        this.token = token;
    }
}
