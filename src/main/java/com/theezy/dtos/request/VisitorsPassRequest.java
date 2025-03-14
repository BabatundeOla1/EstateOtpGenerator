package com.theezy.dtos.request;

import com.theezy.data.models.Tenant;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class VisitorsPassRequest {
    @Id
    private String Id;
    private String name;
    private String phoneNumber;
    private String otpCode;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
    private boolean isValid;
}
