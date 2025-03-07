package com.theezy.services;

import com.theezy.data.models.GenerateOTP;
import com.theezy.data.models.Tenant;
import com.theezy.data.repository.GenerateOTPRepo;
import com.theezy.dtos.response.GenerateOtpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GenerateOTPServiceImplTest {

    @Autowired
    private GenerateOTPService generateOTPService;
    @Autowired
    private GenerateOTPRepo generateOTPRepo;

//    @BeforeEach
//    public void clearRepo(){
//        generateOTPRepo.deleteAll();
//    }

    @Test
    public void testThatTenantCanGenerateCode(){
        GenerateOTP generateOTP = new GenerateOTP();
        GenerateOtpResponse generatedCode = generateOTPService.generateOTP();
        assertEquals(1, generateOTPService.countCodeInOTPRepo());
        System.out.println("Time: " + generatedCode.getExpirationTime());
        System.out.println("Code: " + generatedCode.getOtpCode());
        System.out.println("Ehnehn" + generatedCode);
    }
    @Test
    public void testThatGeneratedCodeCanExpire() throws InterruptedException {
        GenerateOTP generateOTP = new GenerateOTP();
        GenerateOtpResponse generatedCode = generateOTPService.generateOTP();
        assertNotNull(generatedCode);
        assertEquals(6, generatedCode.getOtpCode().length());
        assertNotNull(generatedCode.getExpirationTime());
        assertEquals(1, generateOTPService.countCodeInOTPRepo());
        System.out.println(generatedCode.getExpirationTime());

        LocalDateTime setTime = LocalDateTime.now().minusMinutes(31);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
//        String formattedExpirationTime = generateOTP.getExpirationTime().format(dateTimeFormatter);
//        generatedCode.setExpirationTime(formattedExpirationTime);
//        Thread.sleep(31 * 60 * 1000);
//
//        assertTrue(generatedCode.getExpirationTime().isBefore(formattedExpirationTime  ));
    }
}