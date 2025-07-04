package com.theezy.utils;

import com.theezy.data.models.Tenant;
import com.theezy.dtos.request.TenantRequest;
import com.theezy.dtos.response.TenantResponse;
import com.theezy.utils.passwordEncoder.PasswordHashingService;

public class TenantMapper {

    public static Tenant mapTenantToRequest(TenantRequest tenantRequest){
        Tenant tenant = new Tenant();
        tenant.setName(tenantRequest.getName());
        tenant.setRoomId(tenantRequest.getRoomId());
        tenant.setEmail(tenantRequest.getEmail());
        tenant.setPassword(PasswordHashingService.hashPassword(tenantRequest.getPassword()));
        return tenant;
    }

    public static TenantResponse mapTenantToResponse(Tenant tenant, String accessToken){
        TenantResponse tenantResponse = new TenantResponse();
        tenantResponse.setData(tenant.getName());
        tenantResponse.setStatus(true);
        tenantResponse.setAccessToken(accessToken);
        return tenantResponse;
    }
}

