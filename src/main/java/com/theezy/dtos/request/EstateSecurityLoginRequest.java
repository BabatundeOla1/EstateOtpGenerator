package com.theezy.dtos.request;

import lombok.Data;

@Data
public class EstateSecurityLoginRequest {
    private String email;
    private String password;
}
