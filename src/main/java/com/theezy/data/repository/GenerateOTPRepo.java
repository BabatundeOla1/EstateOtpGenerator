package com.theezy.data.repository;

import com.theezy.data.models.GenerateOTP;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface GenerateOTPRepo extends MongoRepository<GenerateOTP, String> {

    GenerateOTP findByOtpCode(String code);
    void deleteByOtpCode(String otpCode);
    void deleteByExpirationTimeBefore(LocalDateTime expirationTime);
}
