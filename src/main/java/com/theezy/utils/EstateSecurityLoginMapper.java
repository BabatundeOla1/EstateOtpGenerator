package com.theezy.utils;

import com.theezy.data.models.EstateSecurity;
import com.theezy.dtos.request.EstateSecurityLoginRequest;
import com.theezy.dtos.response.EstateSecurityLoginResponse;

public class EstateSecurityLoginMapper {
    public static EstateSecurity mapToRequest(EstateSecurityLoginRequest estateSecurityLoginRequest){
        EstateSecurity estateSecurity = new EstateSecurity();
        estateSecurity.setEmail(estateSecurityLoginRequest.getEmail());
        estateSecurity.setPassword(estateSecurityLoginRequest.getPassword());
        return estateSecurity;
    }

    public static EstateSecurityLoginResponse mapToResponse(EstateSecurity estateSecurity){
        EstateSecurityLoginResponse estateSecurityLoginResponse = new EstateSecurityLoginResponse();
        estateSecurityLoginResponse.setSuccess(true);
        return estateSecurityLoginResponse;
    }
}
