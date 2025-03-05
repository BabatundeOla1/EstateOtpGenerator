package com.theezy.dtos.response;

import lombok.Data;

@Data
public class GenerateOtpResponse {
    private String otpCode;
    private String expirationTime;
}
