package com.theezy.controller;

import com.theezy.dtos.request.TenantLoginRequest;
import com.theezy.dtos.request.TenantRequest;
import com.theezy.dtos.response.GenerateOtpResponse;
import com.theezy.dtos.response.TenantLoginResponse;
import com.theezy.dtos.response.TenantResponse;
import com.theezy.services.GenerateOTPService;
import com.theezy.services.TenantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("")
@RestController
public class TenantController {

    @Autowired
    private TenantServices tenantService;

    @PostMapping("/register")
    public TenantResponse registerTenant(@RequestBody TenantRequest tenantRequest) {
        return tenantService.registerTenant(tenantRequest);
    }
    @PostMapping("/login")
    public TenantLoginResponse tenantLogin(@RequestBody TenantLoginRequest tenantLoginRequest){
        return tenantService.tenantLogin(tenantLoginRequest);
    }
    @GetMapping("/generateOTP")
    public GenerateOtpResponse generateOTP(){
        return tenantService.generateOTP();
    }
}
