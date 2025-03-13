package com.theezy.services;

import com.theezy.data.models.GenerateOTP;
import com.theezy.dtos.request.GenerateOtpRequest;
import com.theezy.dtos.response.GenerateOtpResponse;

public interface GenerateOTPService {

    void deleteExpiredOTPs();
    Long countCodeInOTPRepo();

    GenerateOTP generateOTP();
}
