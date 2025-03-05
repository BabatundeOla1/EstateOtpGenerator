package com.theezy.services;

import com.theezy.data.models.GenerateOTP;
import com.theezy.data.repository.GenerateOTPRepo;
import com.theezy.dtos.request.GenerateOtpRequest;
import com.theezy.exception.OtpExpiredException;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class GenerateOTPServiceImpl implements GenerateOTPService{

    @Autowired
    private GenerateOTPRepo generateOTPRepo;

    @Override
    public GenerateOTP generateOTP() {
        GoogleAuthenticator authenticator = new GoogleAuthenticator();
        GoogleAuthenticatorKey key = authenticator.createCredentials();
        String otp = String.valueOf(authenticator.getTotpPassword(key.getKey()));

        LocalDateTime currentTime = LocalDateTime.now().plusMinutes(30);

//        GenerateOtpRequest generateOtpRequest = new GenerateOtpRequest();
//        generateOtpRequest.setOtpCode(otp);
//        generateOtpRequest.setExpirationTime(currentTime);
        GenerateOTP generatedOtp = createRecords(currentTime, otp);
        generateOTPRepo.save(generatedOtp);
        return generatedOtp;
    }

    @Override
    public GenerateOTP findOtpByCode(String otpCode) {
        return generateOTPRepo.findByOtpCode(otpCode);
    }

    @Override
    public Long countCodeInOTPRepo() {
        return generateOTPRepo.count();
    }

    @Override
    public GenerateOTP validateOTP(String otpCode) {
        GenerateOTP foundCode = findOtpByCode(otpCode);
        boolean isSuccessful = foundCode != null && foundCode.getExpirationTime().isBefore(LocalDateTime.now());
        if (!isSuccessful) {
            throw new OtpExpiredException("Code Already Expired");
        }
        return foundCode;
    }

    @Scheduled(fixedRate = 60000)
    public void deleteExpiredOTPs() {
        LocalDateTime currentTime = LocalDateTime.now();
        generateOTPRepo.deleteByExpirationTimeBefore(currentTime);
    }

    private GenerateOTP createRecords(LocalDateTime expirationTime, String otp){
        GenerateOTP otpRecord = new GenerateOTP();
        otpRecord.setOtpCode(otp);
        otpRecord.setExpirationTime(expirationTime);
        return otpRecord;
    }

    private GenerateOTP createRecords1(GenerateOtpRequest generateOtpRequest){
        GenerateOTP otpRecord = new GenerateOTP();
        otpRecord.setOtpCode(generateOTP().getOtpCode());
        otpRecord.setExpirationTime(generateOtpRequest.getExpirationTime());
        return otpRecord;
    }
}
