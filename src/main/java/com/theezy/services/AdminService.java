package com.theezy.services;

import com.theezy.dtos.request.AdminLoginRequest;
import com.theezy.dtos.response.AdminLoginResponse;

public interface AdminService {
    AdminLoginResponse login(AdminLoginRequest adminLoginRequest);
    void registerAdmin();

}
