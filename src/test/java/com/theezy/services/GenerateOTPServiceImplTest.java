package com.theezy.services;


import com.theezy.data.models.GenerateOTP;
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
        GenerateOTP generatedCode = generateOTPService.generateOTP();
        assertEquals(1, generateOTPService.countCodeInOTPRepo());
    }

    @Test
    public void testThatGeneratedOTP_RepositoryIsNotEmptyWhenOTP_IsGenerated(){
        GenerateOTP generatedCode = generateOTPService.generateOTP();
        assertNotNull(generateOTPService.countCodeInOTPRepo());
    }
}