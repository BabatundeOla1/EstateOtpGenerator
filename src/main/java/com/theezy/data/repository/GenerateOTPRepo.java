package com.theezy.data.repository;

import com.theezy.data.models.GenerateOTP;
import com.theezy.dtos.response.GenerateOtpResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GenerateOTPRepo extends MongoRepository<GenerateOTP, String> {

    Optional<GenerateOTP> findByOtpCode(String code);
    void deleteByExpirationTimeBefore(LocalDateTime expirationTime);
}
