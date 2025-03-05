package com.theezy.utils;

import com.theezy.data.models.GenerateOTP;
import com.theezy.dtos.request.GenerateOtpRequest;
import com.theezy.dtos.response.GenerateOtpResponse;

import java.time.format.DateTimeFormatter;

public class GenerateOtpMapper {

    private static final DateTimeFormatter formatExpirationTime = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
    public static GenerateOTP mapRequest(GenerateOtpRequest generateOtpRequest){
        GenerateOTP generateOTP = new GenerateOTP();
        generateOTP.setOtpCode(generateOtpRequest.getOtpCode());
        generateOTP.setExpirationTime(generateOtpRequest.getExpirationTime());
        return generateOTP;
    }

    public static GenerateOtpResponse mapToResponse(GenerateOTP generateOTP){
        String formattedExpirationTime = generateOTP.getExpirationTime().format(formatExpirationTime);
        GenerateOtpResponse response = new GenerateOtpResponse();
        response.setOtpCode(generateOTP.getOtpCode());
        response.setExpirationTime(formattedExpirationTime);
        return response;
    }
}
