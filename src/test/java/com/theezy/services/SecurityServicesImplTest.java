package com.theezy.services;

import com.theezy.data.repository.EstateSecurityRepository;
import com.theezy.dtos.request.EstateSecurityLoginRequest;
import com.theezy.dtos.request.EstateSecurityRequest;
import com.theezy.dtos.response.EstateSecurityLoginResponse;
import com.theezy.exception.UserAlreadyExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SecurityServicesImplTest {
    @Autowired
    private SecurityService securityService;
    @Autowired
    private EstateSecurityRepository estateSecurityRepository;

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
        estateSecurityRequest.setFirstName("Theezy");
        estateSecurityRequest.setLastName("Olaleye");
        estateSecurityRequest.setEmail("Theezy@gmail.com");
        estateSecurityRequest.setPassword("Password");
        securityService.createAccount(estateSecurityRequest);
        assertEquals(1, securityService.getNumberOfSecurityInRepo());
    }

    @Test
    public void testThatSecurityCanNotRegister_Twice(){
        EstateSecurityRequest estateSecurityRequest = new EstateSecurityRequest();
        estateSecurityRequest.setFirstName("Babatunde");
        estateSecurityRequest.setLastName("Olaleye");
        estateSecurityRequest.setEmail("Babatunde@gmail.com");
        estateSecurityRequest.setPassword("Password");
        securityService.createAccount(estateSecurityRequest);
        assertEquals(1, securityService.getNumberOfSecurityInRepo());
        assertThrows(UserAlreadyExistException.class, ()-> securityService.createAccount(estateSecurityRequest));
    }

    @Test
    public void testThatSecurityCanRegisterAndLoginAfterRegistration(){
        EstateSecurityRequest estateSecurityRequest = new EstateSecurityRequest();
        estateSecurityRequest.setFirstName("Theezy");
        estateSecurityRequest.setLastName("Olaleye");
        estateSecurityRequest.setEmail("Theezy@gmail.com");
        estateSecurityRequest.setPassword("Password");
        securityService.createAccount(estateSecurityRequest);
        assertEquals(1, securityService.getNumberOfSecurityInRepo());

        EstateSecurityLoginRequest estateSecurityLoginRequest = new EstateSecurityLoginRequest();
        estateSecurityLoginRequest.setEmail("Theezy@gmail.com");
        estateSecurityLoginRequest.setPassword("Password");
         EstateSecurityLoginResponse estateSecurityLoginResponse = securityService.login(estateSecurityLoginRequest);
        assertTrue(estateSecurityLoginResponse.isSuccess());
    }

    @Test
    public void testThatEstateSecurityCanValidateGeneratedOTP_code(){
        EstateSecurityRequest estateSecurityRequest = new EstateSecurityRequest();
        estateSecurityRequest.setFirstName("Theezy");
        estateSecurityRequest.setLastName("Olaleye");
        estateSecurityRequest.setEmail("Theezy@gmail.com");
        estateSecurityRequest.setPassword("Password");
        securityService.createAccount(estateSecurityRequest);
        assertEquals(1, securityService.getNumberOfSecurityInRepo());

        EstateSecurityLoginRequest estateSecurityLoginRequest = new EstateSecurityLoginRequest();
        estateSecurityLoginRequest.setEmail("Theezy@gmail.com");
        estateSecurityLoginRequest.setPassword("Password");
        EstateSecurityLoginResponse estateSecurityLoginResponse = securityService.login(estateSecurityLoginRequest);
        assertTrue(estateSecurityLoginResponse.isSuccess());

        assertNotEquals("780212", securityService.validateOTP("780212"));

    }

}