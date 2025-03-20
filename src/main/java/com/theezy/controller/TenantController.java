package com.theezy.controller;

import com.theezy.data.models.GenerateOTP;
import com.theezy.dtos.request.TenantLoginRequest;
import com.theezy.dtos.request.TenantRequest;
import com.theezy.dtos.response.GenerateOtpResponse;
import com.theezy.dtos.response.TenantLoginResponse;
import com.theezy.dtos.response.TenantResponse;
import com.theezy.exception.UserAlreadyExistException;
import com.theezy.services.TenantServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tenant/")
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class TenantController {

    @Autowired
    private TenantServices tenantService;

    @PostMapping("/register")
    public TenantResponse registerTenant(@Valid @RequestBody TenantRequest tenantRequest) {
        return tenantService.registerTenant(tenantRequest);
    }
    @PostMapping("/login")
    public TenantLoginResponse tenantLogin(@Valid @RequestBody TenantLoginRequest tenantLoginRequest){
        return tenantService.tenantLogin(tenantLoginRequest);
    }
    @PostMapping("/generateOTP")
    public GenerateOTP generateOTP(){
        return tenantService.generateOTP();
    }
}
