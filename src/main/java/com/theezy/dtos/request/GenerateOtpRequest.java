package com.theezy.dtos.request;

import com.theezy.data.models.Tenant;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class GenerateOtpRequest {
    private String otpCode;
    private LocalDateTime expirationTime;
    private boolean isUsed;

}
