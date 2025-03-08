package com.theezy.controller;

import com.theezy.dtos.response.GenerateOtpResponse;
import com.theezy.services.GenerateOTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("")
@RestController
public class GenerateOTPController {

    @Autowired
    private GenerateOTPService generateOTPService;

    @GetMapping
    public GenerateOtpResponse generateOTP(){
        return generateOTPService.generateOTP();
    }
}
