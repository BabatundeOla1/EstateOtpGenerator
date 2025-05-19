package com.theezy.dtos.response;

import lombok.Data;

@Data
public class TenantResponse {
    private boolean status;
    private Object data;
    private String accessToken;
}
