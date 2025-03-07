package com.theezy.services;

import com.theezy.data.models.Tenant;
import com.theezy.dtos.request.TenantLoginRequest;
import com.theezy.dtos.request.TenantRequest;
import com.theezy.dtos.response.GenerateOtpResponse;
import com.theezy.dtos.response.TenantLoginResponse;
import com.theezy.dtos.response.TenantResponse;

public interface TenantServices {

    TenantResponse registerTenant(TenantRequest tenantRequest);

    TenantLoginResponse tenantLogin(TenantLoginRequest tenantLoginRequest);
    Long getNumberOfTenantInRepository();
    GenerateOtpResponse generateOTP();

}
