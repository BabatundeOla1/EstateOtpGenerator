package com.theezy.controller;

import com.theezy.dtos.request.EstateSecurityLoginRequest;
import com.theezy.dtos.request.EstateSecurityRequest;
import com.theezy.dtos.response.EstateSecurityLoginResponse;
import com.theezy.dtos.response.EstateSecurityResponse;
import com.theezy.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("")
@RestController
public class EstateSecurityController {

    @Autowired
    private SecurityService securityService;

    @PostMapping("/securityRegister")
    public EstateSecurityResponse createEstateSecurity(@RequestBody EstateSecurityRequest estateSecurityRequest){
        return securityService.createAccount(estateSecurityRequest);
    }
    @PostMapping("/securityLogin")
    public EstateSecurityLoginResponse loginEstateSecurity(@RequestBody EstateSecurityLoginRequest estateSecurityLoginRequest){
        return securityService.login(estateSecurityLoginRequest);
    }
//    @PostMapping("/validateOTP")
//    public GenerateOtpResponse
}
