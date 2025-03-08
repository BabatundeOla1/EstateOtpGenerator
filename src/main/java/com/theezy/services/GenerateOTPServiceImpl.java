package com.theezy.services;

import com.theezy.data.models.GenerateOTP;
import com.theezy.data.repository.GenerateOTPRepo;
import com.theezy.dtos.request.GenerateOtpRequest;
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
    public GenerateOtpResponse generateOTP() {
        GoogleAuthenticator authenticator = new GoogleAuthenticator();
        GoogleAuthenticatorKey key = authenticator.createCredentials();
        String otp = String.valueOf(authenticator.getTotpPassword(key.getKey()));

        LocalDateTime currentTime = LocalDateTime.now().plusMinutes(30);

        GenerateOtpRequest generateOtpRequest = new GenerateOtpRequest();
        generateOtpRequest.setOtpCode(otp);
        generateOtpRequest.setExpirationTime(currentTime);
        GenerateOTP generateOTP = createRecords(generateOtpRequest);
        generateOTPRepo.save(generateOTP);
        return  GenerateOtpMapper.mapToResponse(generateOTP);
    }

//    @Override
//    public GenerateOTP generateOTP2() {
//        GoogleAuthenticator authenticator = new GoogleAuthenticator();
//        GoogleAuthenticatorKey key = authenticator.createCredentials();
//        String otp = String.valueOf(authenticator.getTotpPassword(key.getKey()));
//
//        LocalDateTime currentTime = LocalDateTime.now().plusMinutes(30);
//
//        GenerateOTP generatedOtp = createRecords2(currentTime, otp);
//        generateOTPRepo.save(generatedOtp);
//        return generatedOtp;
//    }


    @Override
    public Long countCodeInOTPRepo() {
        return generateOTPRepo.count();
    }

    @Scheduled(fixedRate = 60000)
    public void deleteExpiredOTPs() {
        LocalDateTime currentTime = LocalDateTime.now();
        generateOTPRepo.deleteByExpirationTimeBefore(currentTime);
    }

    private GenerateOTP createRecords(GenerateOtpRequest generateOtpRequest){
        return GenerateOtpMapper.mapRequest(generateOtpRequest);
    }
//    private GenerateOTP createRecords2(LocalDateTime expirationTime, String otp){
//        GenerateOTP otpRecord = new GenerateOTP();
//        otpRecord.setOtpCode(otp);
//        otpRecord.setExpirationTime(expirationTime);
//        return otpRecord;
//    }

}
