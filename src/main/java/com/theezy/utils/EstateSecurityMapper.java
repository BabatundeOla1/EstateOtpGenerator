package com.theezy.utils;

import com.theezy.data.models.EstateSecurity;
import com.theezy.dtos.request.EstateSecurityRequest;
import com.theezy.dtos.response.EstateSecurityResponse;
import com.theezy.utils.passwordEncoder.PasswordHashingService;

public class EstateSecurityMapper {
    public static EstateSecurity mapToRequest(EstateSecurityRequest estateSecurityRequest){
        EstateSecurity estateSecurity = new EstateSecurity();
        estateSecurity.setEmail(estateSecurityRequest.getEmail());
        estateSecurity.setPassword(PasswordHashingService.hashPassword(estateSecurityRequest.getPassword()));
        estateSecurity.setFirstName(estateSecurityRequest.getFirstName());
        estateSecurity.setLastName(estateSecurityRequest.getLastName());
        return estateSecurity;
    }

    public static EstateSecurityResponse mapToResponse(EstateSecurity estateSecurity){
        EstateSecurityResponse estateSecurityResponse = new EstateSecurityResponse();
        estateSecurityResponse.setMessage("Successful");
        estateSecurityResponse.setData(estateSecurity.getId());
        return  estateSecurityResponse;
    }
}
