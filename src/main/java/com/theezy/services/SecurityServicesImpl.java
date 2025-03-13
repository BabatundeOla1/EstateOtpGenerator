package com.theezy.services;

import com.theezy.data.models.EstateSecurity;
import com.theezy.data.models.GenerateOTP;
import com.theezy.data.repository.EstateSecurityRepository;
import com.theezy.data.repository.GenerateOTPRepo;
import com.theezy.dtos.request.EstateSecurityLoginRequest;
import com.theezy.dtos.request.EstateSecurityRequest;
import com.theezy.dtos.request.VisitorsPassRequest;
import com.theezy.dtos.response.EstateSecurityLoginResponse;
import com.theezy.dtos.response.EstateSecurityResponse;
import com.theezy.dtos.response.GenerateOtpResponse;
import com.theezy.exception.OtpExpiredException;
import com.theezy.exception.UserAlreadyExistException;
import com.theezy.utils.EstateSecurityLoginMapper;
import com.theezy.utils.EstateSecurityMapper;
import com.theezy.utils.GenerateOtpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SecurityServicesImpl implements SecurityService{

    @Autowired
    private GenerateOTPRepo generateOTPRepo;
    @Autowired
    private EstateSecurityRepository estateSecurityRepository;

    @Override
    public GenerateOtpResponse validateOTP(String otpCode) {
        GenerateOTP foundCode = findGenerateOTPByOtpCode(otpCode);
        boolean isSuccessful = foundCode != null && foundCode.getExpirationTime().isBefore(LocalDateTime.now());

        if (isSuccessful) {
            throw new OtpExpiredException("Code Already Expired");
        }
        return GenerateOtpMapper.mapToResponse(foundCode);
    }


//    @Override
//    public GenerateOtpResponse checkOtpValidityAndCreateAVisitorPass(String otpCode, String visitorsName, String visitorPhoneNumber) {
//        GenerateOtpResponse validatedCode = checkOtpValidity(otpCode);
//
//        VisitorsPassRequest visitorsPassRequest = new VisitorsPassRequest();
//        visitorsPassRequest.setName(visitorsName);
//        visitorsPassRequest.setPhoneNumber(visitorPhoneNumber);
//        visitorsPassRequest.setTimeIn(LocalDateTime.now());
//        visitorsPassRequest.setValid(Boolean.FALSE);
//        return validatedCode;
//    }

    @Override
    public EstateSecurityResponse createAccount(EstateSecurityRequest estateSecurityRequest) {
        if (checkIfUserExist(estateSecurityRequest.getEmail())){
            throw new UserAlreadyExistException("Security already exist");
        }
        EstateSecurity estateSecurity = EstateSecurityMapper.mapToRequest(estateSecurityRequest);
        estateSecurityRepository.save(estateSecurity);
        return EstateSecurityMapper.mapToResponse(estateSecurity);
    }

    @Override
    public EstateSecurityLoginResponse login(EstateSecurityLoginRequest estateSecurityLoginRequest) {
        EstateSecurity foundEstateSecurity = estateSecurityRepository.findByEmail(estateSecurityLoginRequest.getEmail());
        boolean isSuccessful = foundEstateSecurity.getPassword().equals(estateSecurityLoginRequest.getPassword());
        if (!isSuccessful){
            throw new IllegalArgumentException("Invalid Password");
        }
        return EstateSecurityLoginMapper.mapToResponse(foundEstateSecurity);
    }

    @Override
    public Long getNumberOfSecurityInRepo() {
        return estateSecurityRepository.count();
    }

    private boolean checkIfUserExist(String email){
        return estateSecurityRepository.existsByEmail(email);
    }
    private GenerateOTP findGenerateOTPByOtpCode(String otpCode) {
        return generateOTPRepo.findByOtpCode(otpCode).orElseThrow(()->new OtpExpiredException("Invalid OTP"));
    }

//    private GenerateOtpResponse checkOtpValidity(String otpCode){
//        GenerateOtpResponse foundCode = findGenerateOTPByOtpCode(otpCode);
//        boolean isSuccessful = foundCode != null && foundCode.getExpirationTime().isBefore(LocalDateTime.now());
//        if (!isSuccessful) {
//            throw new OtpExpiredException("Code Already Expired");
//        }
//        return foundCode;
//    }
}
