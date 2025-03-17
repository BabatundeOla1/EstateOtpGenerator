package com.theezy.services;

import com.theezy.data.models.GenerateOTP;
import com.theezy.data.repository.GenerateOTPRepo;
import com.theezy.dtos.request.GenerateOtpRequest;
import com.theezy.dtos.request.TenantRequest;
import com.theezy.dtos.response.GenerateOtpResponse;
import com.theezy.utils.GenerateOtpMapper;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GenerateOTPServiceImpl implements GenerateOTPService{

    @Autowired
    private GenerateOTPRepo generateOTPRepo;

    @Override
    public GenerateOTP generateOTP() {
        GoogleAuthenticator authenticator = new GoogleAuthenticator();
        GoogleAuthenticatorKey key = authenticator.createCredentials();
        String otp = String.format("%06d", authenticator.getTotpPassword(key.getKey()));

        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(30);

        GenerateOTP generateOTP = createRecords(expirationTime, otp);
        return  generateOTP;
    }
    @Override
    public Long countCodeInOTPRepo() {
        return generateOTPRepo.count();
    }

    @Override
    @Scheduled(fixedRate = 10000)
    public void deleteExpiredOTPs() {
        LocalDateTime currentTime = LocalDateTime.now();
        generateOTPRepo.deleteByExpirationTimeBefore(currentTime);
    }
    private GenerateOTP createRecords(LocalDateTime expirationTime, String otp){
        GenerateOTP generateOTP = new GenerateOTP();
        generateOTP.setOtpCode(otp);
        generateOTP.setExpirationTime(expirationTime);
        generateOTP.setUsed(Boolean.FALSE);
        generateOTPRepo.save(generateOTP);
        return generateOTP;
    }
}
