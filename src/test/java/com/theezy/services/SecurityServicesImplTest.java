package com.theezy.services;

import com.theezy.data.models.GenerateOTP;
import com.theezy.data.repository.EstateSecurityRepository;
import com.theezy.data.repository.GenerateOTPRepo;
import com.theezy.dtos.request.EstateSecurityLoginRequest;
import com.theezy.dtos.request.EstateSecurityRequest;
import com.theezy.dtos.request.GenerateOtpRequest;
import com.theezy.dtos.response.EstateSecurityLoginResponse;
import com.theezy.dtos.response.GenerateOtpResponse;
import com.theezy.exception.UserAlreadyExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SecurityServicesImplTest {
    @Autowired
    private SecurityService securityService;
    @Autowired
    private EstateSecurityRepository estateSecurityRepository;
    @Autowired
    private GenerateOTPRepo generateOTPRepo;

    public void setEstateSecurityRegistration(EstateSecurityRequest estateSecurityRequest){
        estateSecurityRequest.setFirstName("Theezy");
        estateSecurityRequest.setLastName("Olaleye");
        estateSecurityRequest.setEmail("Theezy@gmail.com");
        estateSecurityRequest.setPassword("Password");
    }

    public void setEstateSecurityLogin(EstateSecurityLoginRequest estateSecurityLoginRequest){
        estateSecurityLoginRequest.setEmail("Theezy@gmail.com");
        estateSecurityLoginRequest.setPassword("Password");
    }
    @BeforeEach
    public void clearRepositoryAfterEachTest(){
        estateSecurityRepository.deleteAll();
    }

    @Test
    public void testThatRepositoryIsEmptyFirst(){
        assertEquals(0, securityService.getNumberOfSecurityInRepo());
    }
    @Test
    public void testThatEstateSecurityCanRegister(){
        EstateSecurityRequest estateSecurityRequest = new EstateSecurityRequest();
        setEstateSecurityRegistration(estateSecurityRequest);
        securityService.createAccount(estateSecurityRequest);
        assertEquals(1, securityService.getNumberOfSecurityInRepo());
    }

    @Test
    public void testThatSecurityCanNotRegister_Twice(){
        EstateSecurityRequest estateSecurityRequest = new EstateSecurityRequest();
        setEstateSecurityRegistration(estateSecurityRequest);
        securityService.createAccount(estateSecurityRequest);
        assertEquals(1, securityService.getNumberOfSecurityInRepo());
        assertThrows(UserAlreadyExistException.class, ()-> securityService.createAccount(estateSecurityRequest));
    }

    @Test
    public void testThatSecurityCanRegisterAndLoginAfterRegistration(){
        EstateSecurityRequest estateSecurityRequest = new EstateSecurityRequest();
        setEstateSecurityRegistration(estateSecurityRequest);
        securityService.createAccount(estateSecurityRequest);
        assertEquals(1, securityService.getNumberOfSecurityInRepo());

        EstateSecurityLoginRequest estateSecurityLoginRequest = new EstateSecurityLoginRequest();
        setEstateSecurityLogin(estateSecurityLoginRequest);
         EstateSecurityLoginResponse estateSecurityLoginResponse = securityService.login(estateSecurityLoginRequest);
        assertTrue(estateSecurityLoginResponse.isSuccess());
    }

    @Test
    public void testThatEstateSecurityCanValidateGeneratedOTP_code(){
        EstateSecurityRequest estateSecurityRequest = new EstateSecurityRequest();
        setEstateSecurityRegistration(estateSecurityRequest);
        securityService.createAccount(estateSecurityRequest);
        assertEquals(1, securityService.getNumberOfSecurityInRepo());

        EstateSecurityLoginRequest estateSecurityLoginRequest = new EstateSecurityLoginRequest();
        setEstateSecurityLogin(estateSecurityLoginRequest);
        EstateSecurityLoginResponse estateSecurityLoginResponse = securityService.login(estateSecurityLoginRequest);
        assertTrue(estateSecurityLoginResponse.isSuccess());

        GenerateOTP generateOtpRequest = new GenerateOTP();
        generateOtpRequest.setOtpCode("123456");
        generateOtpRequest.setExpirationTime(LocalDateTime.now().plusMinutes(30));
        generateOTPRepo.save(generateOtpRequest);

        GenerateOtpResponse validateCode = securityService.validateOTP2("123456");


//        assertEquals(generateOtpRequest, securityService.validateOTP(generateOtpRequest.getOtpCode()));
    }
}