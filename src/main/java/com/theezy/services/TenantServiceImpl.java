package com.theezy.services;

import com.theezy.data.models.Apartment;
import com.theezy.data.models.GenerateOTP;
import com.theezy.data.models.Role;
import com.theezy.data.models.Tenant;
import com.theezy.data.repository.ApartmentRepository;
import com.theezy.data.repository.GenerateOTPRepo;
import com.theezy.data.repository.TenantRepository;
import com.theezy.dtos.request.TenantLoginRequest;
import com.theezy.dtos.request.TenantRequest;
import com.theezy.dtos.response.GenerateOtpResponse;
import com.theezy.dtos.response.TenantLoginResponse;
import com.theezy.dtos.response.TenantResponse;
import com.theezy.exception.ApartmentNotFoundException;
import com.theezy.exception.ApartmentOccupiedException;
import com.theezy.exception.UserAlreadyExistException;
import com.theezy.utils.TenantLoginMapper;
import com.theezy.utils.TenantMapper;
import com.theezy.utils.passwordEncoder.PasswordHashingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantServiceImpl implements TenantServices{
    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private GenerateOTPService generateOTPService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Override
    public TenantResponse registerTenant(TenantRequest tenantRequest) {
        validateTenantDetails(tenantRequest);

        if (checkIfUserExist(tenantRequest.getEmail())){
            throw new UserAlreadyExistException("Tenant already exist");
        }

        Apartment myApartment = apartmentRepository.findApartmentByHouseNumber(tenantRequest.getRoomId())
                .orElseThrow(()-> new ApartmentNotFoundException("Apartment not found or Invalid Apartment number"));

        if (myApartment.isOccupied()){
            throw new ApartmentOccupiedException("Apartment has been occupied");
        }

        Tenant tenant = TenantMapper.mapTenantToRequest(tenantRequest);

        myApartment.setOwnerEmail(tenant.getEmail());
        myApartment.setOccupied(true);

        tenant.setMyApartment(myApartment);
        tenant.setRole(Role.TENANT);
        tenantRepository.save(tenant);
        apartmentRepository.save(myApartment);

        String accessToken = jwtService.generateAccessToken(tenant);

        return TenantMapper.mapTenantToResponse(tenant, accessToken);
//        return TenantMapper.mapTenantToResponse(tenant);
    }


    @Override
    public TenantLoginResponse tenantLogin(TenantLoginRequest tenantLoginRequest) {

        if (tenantLoginRequest.getEmail().isEmpty() || tenantLoginRequest.getEmail().isBlank()){
            throw new IllegalArgumentException("Space can not be Empty");
        }

        Tenant foundTenant = findTenantByEmail(tenantLoginRequest.getEmail());

        boolean isSuccessful = PasswordHashingService.checkPassword(tenantLoginRequest.getPassword(), (foundTenant.getPassword()));
        if (!isSuccessful){
            throw new IllegalArgumentException("Invalid Credentials");
        }
        String accessToken = jwtService.generateAccessToken(foundTenant);
        return TenantLoginMapper.mapToTenantLoginResponse(accessToken,  "Login Successful, OTP Generated");
    }

    @Override
    public GenerateOTP generateOTP() {
        return generateOTPService.generateOTP();
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
