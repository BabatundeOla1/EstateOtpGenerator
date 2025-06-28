package com.theezy.services;

import com.theezy.data.models.Admin;
import com.theezy.data.models.Role;
import com.theezy.data.repository.AdminRepository;
import com.theezy.data.repository.UserRepository;
import com.theezy.dtos.request.AdminLoginRequest;
import com.theezy.dtos.response.AdminLoginResponse;
import com.theezy.exception.AdminNotFoundException;
import com.theezy.utils.AdminLoginMapper;
import com.theezy.utils.passwordEncoder.PasswordHashingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
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

    @Autowired
    private UserRepository userRepository;

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
    }

    @Override
    public void registerAdmin() {
        if (adminRepository.findAdminByEmail(adminEmail).isEmpty()) {
            Admin permanentAdmin = new Admin();
            permanentAdmin.setEmail(adminEmail);
            permanentAdmin.setPassword(PasswordHashingService.hashPassword(adminPassword));
            permanentAdmin.setRole(Role.ADMIN);
            adminRepository.save(permanentAdmin);
//            userRepository.save(permanentAdmin);
            log.info(String.valueOf("Admin email: " + permanentAdmin.getEmail()));
            log.info(String.valueOf("Admin role: " + permanentAdmin.getRole()));
            log.info(String.valueOf("Admin password: " + permanentAdmin.getPassword()));
        }
    }

    public void registerAdminTest(Admin admin) {
        if (adminRepository.findAdminByEmail(admin.getEmail()).isEmpty()) {
            admin.setPassword(PasswordHashingService.hashPassword(admin.getPassword()));
            admin.setRole(Role.ADMIN);
            adminRepository.save(admin);
            log.info("Admin email: {}", admin.getEmail());
            log.info("Admin role: {}", admin.getRole());
            log.info("Admin password: {}", admin.getPassword());
        } else {
            log.info("Admin with email {} already exists", admin.getEmail());
        }
    }
}