package com.theezy.utils;

import com.theezy.data.models.Tenant;
import com.theezy.dtos.request.TenantLoginRequest;
import com.theezy.dtos.response.TenantLoginResponse;

public class TenantLoginMapper {
    public static Tenant mapToTenant(TenantLoginRequest tenantLoginRequest){
        Tenant tenant = new Tenant();
        tenant.setEmail(tenantLoginRequest.getEmail());
        tenant.setPassword(tenantLoginRequest.getPassword());
        return tenant;
    }

    public static TenantLoginResponse mapToTenantLoginResponse (String message){
        TenantLoginResponse tenantLoginResponse = new TenantLoginResponse();
        tenantLoginResponse.setSuccess(true);
        return tenantLoginResponse;
    }
}
