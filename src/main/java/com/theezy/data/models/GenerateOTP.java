package com.theezy.data.models;

import lombok.Data;

import java.sql.Time;
import java.time.LocalDateTime;
@Data
public class GenerateOTP {
    private String otpCode;
    private LocalDateTime expirationTime;
//    private String tenantName;
//    private String tenantEmail;
}
