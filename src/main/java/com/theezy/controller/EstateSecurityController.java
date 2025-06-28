package com.theezy.controller;

import com.theezy.data.models.GenerateOTP;
import com.theezy.data.models.VisitorsPass;
import com.theezy.data.repository.VisitorsPassRepo;
import com.theezy.dtos.request.EstateSecurityLoginRequest;
import com.theezy.dtos.request.EstateSecurityRequest;
import com.theezy.dtos.request.VisitorsPassRequest;
import com.theezy.dtos.response.EstateSecurityLoginResponse;
import com.theezy.dtos.response.EstateSecurityResponse;
import com.theezy.dtos.response.GenerateOtpResponse;
import com.theezy.services.SecurityService;
import com.theezy.services.VisitorsPassService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/security/")
@RestController
public class EstateSecurityController {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private VisitorsPassService visitorsPassService;

    @PostMapping("/securityRegister")
    @PreAuthorize("hasRole('ADMIN')")
    public EstateSecurityResponse createEstateSecurity(@Valid @RequestBody EstateSecurityRequest estateSecurityRequest){
        log.info(String.valueOf(estateSecurityRequest));
        EstateSecurityResponse response = securityService.createAccount(estateSecurityRequest);
        log.info(String.valueOf(response));
        return response;
    }
    @PostMapping("/securityLogin")
    public EstateSecurityLoginResponse loginEstateSecurity(@Valid @RequestBody EstateSecurityLoginRequest estateSecurityLoginRequest){
        return securityService.login(estateSecurityLoginRequest);
    }
    @PostMapping("/validateOtp")
    public GenerateOtpResponse validateVisitorOtp(@RequestBody VisitorsPassRequest visitorsPassRequest){
        return securityService.validateOTP(visitorsPassRequest);
    }
    @PostMapping("/visitorCheckOut")
    public VisitorsPass validateCheckOutUser(@RequestBody VisitorsPassRequest visitorsPassRequest){
        return visitorsPassService.validateForCheckOut(visitorsPassRequest);
    }
}
