package com.theezy.services;


import com.theezy.data.repository.GenerateOTPRepo;
import com.theezy.dtos.response.GenerateOtpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


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
        GenerateOtpResponse generatedCode = generateOTPService.generateOTP();
        assertEquals(1, generateOTPService.countCodeInOTPRepo());
    }

    @Test
    public void testThatGeneratedOTP_RepositoryIsNotEmptyWhenOTP_IsGenerated(){
        GenerateOtpResponse generatedCode = generateOTPService.generateOTP();
        assertNotNull(generateOTPService.countCodeInOTPRepo());
    }
    @Test
    public void testThatGeneratedCodeCanExpire(){
        GenerateOtpResponse generatedCode = generateOTPService.generateOTP();
        assertNotNull(generatedCode);
        assertEquals(6, generatedCode.getOtpCode().length());
        assertNotNull(generatedCode.getExpirationTime());
        assertEquals(1, generateOTPService.countCodeInOTPRepo());

        LocalDateTime currentTime = LocalDateTime.now();
//        LocalDateTime expirationTime = LocalDateTime.parse(generatedCode.getExpirationTime());
        LocalDateTime expirationTime = generatedCode.getExpirationTime();
        assertTrue(expirationTime.isAfter(currentTime));
        System.out.println(generatedCode);

    }

    @Test
    public void testThatOTP_IsDeletedAutomaticallyWhenExpired(){
        GenerateOtpResponse generatedOtp = generateOTPService.generateOTP();
        assertEquals(1, generateOTPService.countCodeInOTPRepo());
        assertEquals(6, generatedOtp.getOtpCode().length());

        LocalDateTime  currentTime = LocalDateTime.now();
        generatedOtp.setExpirationTime(currentTime.minusMinutes(30));
        generateOTPService.deleteExpiredOTPs();
        assertEquals(1, generateOTPService.countCodeInOTPRepo());
        System.out.println(currentTime);
        System.out.println(generatedOtp.getExpirationTime());
        System.out.println(generatedOtp);

    }
}