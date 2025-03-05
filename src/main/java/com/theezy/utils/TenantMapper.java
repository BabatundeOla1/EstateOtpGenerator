package com.theezy.utils;

import com.theezy.data.models.Tenant;
import com.theezy.dtos.request.TenantRequest;
import com.theezy.dtos.response.TenantResponse;

public class TenantMapper {

    public static Tenant mapTenantToRequest(TenantRequest tenantRequest){
        Tenant tenant = new Tenant();
        tenant.setName(tenantRequest.getName());
        tenant.setRoomId(tenantRequest.getRoomId());
        tenant.setEmail(tenantRequest.getEmail());
        tenant.setPassword(tenantRequest.getPassword());
        return tenant;
    }

    public static TenantResponse mapTenantToResponse(Tenant tenant){
        TenantResponse tenantResponse = new TenantResponse();
        tenantResponse.setData(tenant.getName());
        tenantResponse.setStatus(true);
        return tenantResponse;
    }
}

