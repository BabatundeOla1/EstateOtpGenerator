package com.theezy.dtos.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GenerateOtpResponse {
    private String otpCode;
    private LocalDateTime expirationTime;
}
