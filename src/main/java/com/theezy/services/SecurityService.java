package com.theezy.services;

import com.theezy.data.models.GenerateOTP;
import com.theezy.dtos.request.EstateSecurityLoginRequest;
import com.theezy.dtos.request.EstateSecurityRequest;
import com.theezy.dtos.response.EstateSecurityLoginResponse;
import com.theezy.dtos.response.EstateSecurityResponse;
import com.theezy.dtos.response.GenerateOtpResponse;

public interface SecurityService {

    GenerateOTP validateOTP(String otpCode);
    GenerateOtpResponse validateOTP2(String otpCode);

//    GenerateOTP findOtpByCode(String otpCode);

    EstateSecurityResponse createAccount(EstateSecurityRequest estateSecurityRequest);

    EstateSecurityLoginResponse login(EstateSecurityLoginRequest estateSecurityLoginRequest);
    Long getNumberOfSecurityInRepo();
}
