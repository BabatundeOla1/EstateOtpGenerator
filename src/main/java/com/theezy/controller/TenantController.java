package com.theezy.controller;

import com.theezy.data.models.GenerateOTP;
import com.theezy.data.models.Role;
import com.theezy.dtos.request.TenantLoginRequest;
import com.theezy.dtos.request.TenantRequest;
import com.theezy.dtos.response.TenantLoginResponse;
import com.theezy.dtos.response.TenantResponse;
import com.theezy.services.TenantServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tenant/")
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class TenantController {

    @Autowired
    private TenantServices tenantService;

    @PostMapping("/register")
    public ResponseEntity<TenantResponse> registerTenant(@Valid @RequestBody TenantRequest tenantRequest) {
        return new ResponseEntity<>(tenantService.registerTenant(tenantRequest), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<TenantLoginResponse> tenantLogin(@Valid @RequestBody TenantLoginRequest tenantLoginRequest){
        return new ResponseEntity<>(tenantService.tenantLogin(tenantLoginRequest), HttpStatus.OK);
    }
    @GetMapping("/generateOTP")
    @PreAuthorize("hasRole('TENANT')")
    public ResponseEntity<GenerateOTP> generateOTP(){
        return new ResponseEntity<>(tenantService.generateOTP(), HttpStatus.OK);
    }

    @GetMapping("/viewAllTenant")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> getNumberOfTenants(){
        return new ResponseEntity<>(tenantService.getNumberOfTenantInRepository(), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        System.out.println("Test endpoint hit");
        return ResponseEntity.ok("Hello from test");
    }

}
