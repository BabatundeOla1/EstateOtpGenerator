package com.theezy.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.sql.Time;
import java.time.LocalDateTime;
@Data
public class GenerateOTP {
    @Id
    private String id;
    private String otpCode;
    private LocalDateTime expirationTime;
    private boolean isUsed;
}
