package com.theezy.dtos.request;

import lombok.Data;

@Data
public class AdminLoginRequest {
    private String email;
    private String password;
}
