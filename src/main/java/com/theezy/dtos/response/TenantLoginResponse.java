package com.theezy.dtos.response;

import lombok.Data;

@Data
public class TenantLoginResponse {
    private boolean isSuccess;
    private String accessToken;

}
