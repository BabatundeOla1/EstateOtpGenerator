package com.theezy.services;

import com.theezy.data.models.GenerateOTP;
import com.theezy.dtos.response.GenerateOtpResponse;

public interface GenerateOTPService {

    GenerateOtpResponse generateOTP();


    Long countCodeInOTPRepo();

    void deleteExpiredOTPs();
}
