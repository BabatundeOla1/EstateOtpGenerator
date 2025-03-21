package com.theezy.services;

import com.theezy.data.models.GenerateOTP;
import com.theezy.data.models.Tenant;
import com.theezy.data.repository.GenerateOTPRepo;
import com.theezy.data.repository.TenantRepository;
import com.theezy.dtos.request.TenantLoginRequest;
import com.theezy.dtos.request.TenantRequest;
import com.theezy.dtos.response.GenerateOtpResponse;
import com.theezy.dtos.response.TenantLoginResponse;
import com.theezy.dtos.response.TenantResponse;
import com.theezy.exception.UserAlreadyExistException;
import com.theezy.utils.TenantLoginMapper;
import com.theezy.utils.TenantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantServiceImpl implements TenantServices{
    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private GenerateOTPService generateOTPService;

    @Autowired
    private GenerateOTPRepo generateOTPRepo;


    @Override
    public TenantResponse registerTenant(TenantRequest tenantRequest) {
        validateTenantDetails(tenantRequest);
        if (checkIfUserExist(tenantRequest.getEmail())){
            throw new UserAlreadyExistException("Tenant already exist");
        }
        Tenant tenant = TenantMapper.mapTenantToRequest(tenantRequest);
        tenantRepository.save(tenant);
        return TenantMapper.mapTenantToResponse(tenant);
    }
    @Override
    public TenantLoginResponse tenantLogin(TenantLoginRequest tenantLoginRequest) {

        if (tenantLoginRequest.getEmail().isEmpty() || tenantLoginRequest.getEmail().isBlank()){
            throw new IllegalArgumentException("Space can not be Empty");
        }

        Tenant foundTenant = findTenantByEmail(tenantLoginRequest.getEmail());
        boolean isSuccessful = foundTenant.getPassword().equals(tenantLoginRequest.getPassword());
        if (!isSuccessful){
            throw new IllegalArgumentException("Invalid Password");
        }
        return TenantLoginMapper.mapToTenantLoginResponse("Login Successful, OTP Generated");
    }

    @Override
    public GenerateOTP generateOTP() {
        GenerateOTP generateOTP = generateOTPService.generateOTP();
        return generateOTP;
    }


    @Override
    public Long getNumberOfTenantInRepository() {
        return tenantRepository.count();
    }

    private Tenant findTenantByEmail(String email) {
        return tenantRepository.findTenantByEmail(email);
    }
    private boolean checkIfUserExist(String email){
        return tenantRepository.existsTenantByEmail(email);
    }

    private void validateTenantDetails(TenantRequest tenantRequest){
        if (tenantRequest.getName().isEmpty() || tenantRequest.getName().isBlank()){
            throw new IllegalArgumentException("Space can not be Empty");
        }

        if (tenantRequest.getRoomId().isEmpty() || tenantRequest.getRoomId().isBlank()){
            throw new IllegalArgumentException("Space can not be Empty");
        }
        if (tenantRequest.getEmail().isEmpty() || tenantRequest.getEmail().isBlank()){
            throw new IllegalArgumentException("Space can not be Empty");
        }

        if (tenantRequest.getPassword().isEmpty() || tenantRequest.getPassword().isBlank()){
            throw new IllegalArgumentException("Space can not be Empty");
        }
    }
}
