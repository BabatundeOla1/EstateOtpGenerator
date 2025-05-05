package com.theezy.utils;

import com.theezy.data.models.Admin;
import com.theezy.dtos.request.AdminLoginRequest;
import com.theezy.dtos.request.AdminRegisterRequest;
import com.theezy.dtos.response.AdminLoginResponse;

public class AdminLoginMapper {

    public static Admin mapAdminLoginRequestToAdmin(AdminLoginRequest adminLoginRequest){
        Admin admin = new Admin();
        admin.setEmail(adminLoginRequest.getEmail());
        admin.setPassword(adminLoginRequest.getPassword());
        return admin;
    }
//    public static AdminLoginResponse mapLoginRequestToResponse(Admin admin, String accessToken, String refreshToken){
//        AdminLoginResponse adminLoginResponse = new AdminLoginResponse();
//        adminLoginResponse.setMessage("Login Successful");
//        adminLoginResponse.setData(admin.getEmail());
//        adminLoginResponse.setAccessToken(accessToken);
//        adminLoginResponse.setRefreshToken(refreshToken);
//        return adminLoginResponse;
//    }
    public static AdminLoginResponse mapLoginRequestToResponse(Admin admin){
        AdminLoginResponse adminLoginResponse = new AdminLoginResponse();
        adminLoginResponse.setMessage("Login Successful");
        adminLoginResponse.setData(admin.getEmail());
        return adminLoginResponse;
    }
}
