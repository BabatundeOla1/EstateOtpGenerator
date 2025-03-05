package com.theezy.dtos.request;

import lombok.Data;

@Data
public class TenantLoginRequest {
    private String email;
    private String password;

}
