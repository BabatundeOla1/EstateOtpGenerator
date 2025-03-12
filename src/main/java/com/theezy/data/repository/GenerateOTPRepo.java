package com.theezy.data.repository;

import com.theezy.data.models.GenerateOTP;
import com.theezy.dtos.response.GenerateOtpResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface GenerateOTPRepo extends MongoRepository<GenerateOTP, String> {

    GenerateOTP findByOtpCode(String code);
    GenerateOtpResponse findGenerateOTPByOtpCode(String code);
    void deleteByOtpCode(String otpCode);
    void deleteByExpirationTimeBefore(LocalDateTime expirationTime);
}
