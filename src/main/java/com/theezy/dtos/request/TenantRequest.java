package com.theezy.dtos.request;

import lombok.Data;

@Data
public class TenantRequest {
    private String name;
    private String roomId;
    private String email;
    private String password;
}
