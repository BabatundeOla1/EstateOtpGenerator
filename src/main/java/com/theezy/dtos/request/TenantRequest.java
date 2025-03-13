package com.theezy.dtos.request;

import com.theezy.data.models.GenerateOTP;
import lombok.Data;

@Data
public class TenantRequest {
    private String name;
    private String roomId;
    private String email;
    private String password;
//    private String generateOTP;
}
