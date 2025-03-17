package com.theezy.utils;

import com.theezy.data.models.GenerateOTP;
import com.theezy.data.models.Tenant;
import com.theezy.data.models.VisitorsPass;
import com.theezy.dtos.request.GenerateOtpRequest;
import com.theezy.dtos.response.GenerateOtpResponse;

public class GenerateOtpMapper {

    public static GenerateOTP mapRequest(GenerateOtpRequest generateOtpRequest){
        GenerateOTP generateOTP = new GenerateOTP();
        generateOTP.setOtpCode(generateOtpRequest.getOtpCode());
        generateOTP.setExpirationTime(generateOtpRequest.getExpirationTime());
        return generateOTP;
    }

    public static GenerateOtpResponse mapToResponse(GenerateOTP generateOTP, VisitorsPass visitorPass){
        GenerateOtpResponse response = new GenerateOtpResponse();
        response.setOtpCode(generateOTP.getOtpCode());
        response.setExpirationTime(generateOTP.getExpirationTime());
        response.setVisitorsPass(visitorPass);
        return response;
    }
}
