package com.theezy.controller;

import com.theezy.dtos.request.AdminLoginRequest;
import com.theezy.dtos.response.AdminLoginResponse;
import com.theezy.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin/")
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("login")
    public ResponseEntity<AdminLoginResponse> adminLogin(@RequestBody AdminLoginRequest adminLoginRequest){
        return new ResponseEntity<>(adminService.login(adminLoginRequest), HttpStatus.OK);
    }

}
