package com.theezy.services;

import com.theezy.dtos.response.GenerateOtpResponse;

public interface GenerateOTPService {

    GenerateOtpResponse generateOTP();

//    GenerateOTP generateOTP2();

    Long countCodeInOTPRepo();

//    void deleteExpiredOTPs();
}
