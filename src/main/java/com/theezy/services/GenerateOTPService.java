package com.theezy.services;

import com.theezy.data.models.GenerateOTP;

public interface GenerateOTPService {

    GenerateOTP generateOTP();
    GenerateOTP findOtpByCode(String otpCode);

    Long countCodeInOTPRepo();

    GenerateOTP validateOTP(String otpCode);
}
