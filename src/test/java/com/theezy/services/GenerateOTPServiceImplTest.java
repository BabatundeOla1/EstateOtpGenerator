package com.theezy.services;

import com.theezy.data.models.GenerateOTP;
import com.theezy.data.models.Tenant;
import com.theezy.data.repository.GenerateOTPRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GenerateOTPServiceImplTest {

    @Autowired
    private GenerateOTPService generateOTPService;
    @Autowired
    private GenerateOTPRepo generateOTPRepo;

    @BeforeEach
    public void clearRepo(){
        generateOTPRepo.deleteAll();
    }

    @Test
    public void testThatTenantCanGenerateCode(){
        GenerateOTP generateOTP = new GenerateOTP();
        GenerateOTP generatedCode = generateOTPService.generateOTP();
        assertEquals(1, generateOTPService.countCodeInOTPRepo());
        System.out.println("Time: " + generatedCode.getExpirationTime());
        System.out.println("Code: " + generatedCode.getOtpCode());
        System.out.println(generateOTP);
    }
    @Test
    public void testThatTenantCanGenerateOTP(){
        ////
    }
}