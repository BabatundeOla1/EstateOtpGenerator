package com.theezy.services;

import com.theezy.data.models.Admin;
import com.theezy.data.models.Role;
import com.theezy.data.repository.AdminRepository;
import com.theezy.dtos.request.AdminLoginRequest;
import com.theezy.dtos.response.AdminLoginResponse;
import com.theezy.exception.AdminNotFoundException;
import com.theezy.utils.AdminLoginMapper;
import com.theezy.utils.passwordEncoder.PasswordHashingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminRepository adminRepository;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    @Autowired
    private JWTService jwtService;

    @Override
    public AdminLoginResponse login(AdminLoginRequest adminLoginRequest) {

        Admin foundAdmin = adminRepository.findAdminByEmail(adminLoginRequest.getEmail())
                .orElseThrow(() -> new AdminNotFoundException("Invalid Email"));

        boolean isPasswordCorrect =PasswordHashingService.checkPassword(adminLoginRequest.getPassword(), foundAdmin.getPassword() );

        if (!isPasswordCorrect){
            throw new AdminNotFoundException("Invalid Password");
        }
        String accessToken = jwtService.generateAccessToken(foundAdmin);
        String refreshToken = jwtService.generateRefreshToken(foundAdmin);

        return AdminLoginMapper.mapLoginRequestToResponse(foundAdmin, accessToken, refreshToken);
//        return AdminLoginMapper.mapLoginRequestToResponse(foundAdmin);
    }

    @Override
    public void registerAdmin() {
        if (adminRepository.findAdminByEmail(adminEmail).isEmpty()) {
            Admin permanentAdmin = new Admin();
            permanentAdmin.setEmail(adminEmail);
            permanentAdmin.setPassword(PasswordHashingService.hashPassword(adminPassword));
            permanentAdmin.setRole(Role.ADMIN);
            adminRepository.save(permanentAdmin);
        }
    }
}