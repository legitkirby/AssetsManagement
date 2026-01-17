package com.isoftstone.assetsmanagement.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String email;
    private String password;

    public String getUsernameOrEmail() {
        return username != null ? username : email;
    }
}