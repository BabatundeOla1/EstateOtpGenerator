package com.theezy.dtos.request;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class GenerateOtpRequest {
    private String otpCode;
    private LocalDateTime expirationTime;
//    private String tenantName;
//    private String tenantEmail;
}
