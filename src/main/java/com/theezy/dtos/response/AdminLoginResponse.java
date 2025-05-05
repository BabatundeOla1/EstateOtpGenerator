package com.theezy.dtos.response;

import lombok.Data;

@Data
public class AdminLoginResponse {
    private String accessToken;
    private String refreshToken;
    private String message;
    private Object data;
}
