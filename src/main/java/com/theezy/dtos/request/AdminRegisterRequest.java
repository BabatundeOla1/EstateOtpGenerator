package com.theezy.dtos.request;

import lombok.Data;

@Data
public class AdminRegisterRequest {
    private String email;
    private String password;
}
