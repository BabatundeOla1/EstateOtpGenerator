package com.theezy.dtos.response;

import com.theezy.data.models.Tenant;
import com.theezy.data.models.VisitorsPass;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GenerateOtpResponse {
    private String otpCode;
    private LocalDateTime expirationTime;
    private boolean isUsed;
    private VisitorsPass visitorsPass;
}
