package com.theezy.services;

import com.theezy.data.models.EstateSecurity;
import com.theezy.data.models.GenerateOTP;
import com.theezy.data.models.VisitorsPass;
import com.theezy.data.repository.EstateSecurityRepository;
import com.theezy.data.repository.GenerateOTPRepo;
import com.theezy.data.repository.VisitorsPassRepo;
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
import com.theezy.utils.passwordEncoder.PasswordHashingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SecurityServicesImpl implements SecurityService{

    @Autowired
    private GenerateOTPRepo generateOTPRepo;
    @Autowired
    private EstateSecurityRepository estateSecurityRepository;

    @Autowired
    private VisitorsPassRepo visitorsPassRepo;

    @Override
    public GenerateOtpResponse validateOTP(VisitorsPassRequest visitorsPassRequest) {
        validateDetails(visitorsPassRequest);

        GenerateOTP foundCode = findGenerateOTPByOtpCode(visitorsPassRequest.getOtpCode());
        boolean isExpired = foundCode != null && foundCode.getExpirationTime().isBefore(LocalDateTime.now());

        if (isExpired) {
            throw new OtpExpiredException("Code Already Expired");
        }

        if (foundCode.isUsed()) {
            throw new OtpExpiredException("Code has already been used");
        }

        foundCode.setUsed(Boolean.TRUE);
        generateOTPRepo.save(foundCode);

        VisitorsPass visitorsPass = new VisitorsPass();
        visitorsPass.setName(visitorsPassRequest.getName());
        visitorsPass.setPhoneNumber(visitorsPassRequest.getPhoneNumber());
        visitorsPass.setTimeIn(LocalDateTime.now());
        visitorsPass.setValid(Boolean.TRUE);
        visitorsPass.setOtpCode(foundCode.getOtpCode());
        visitorsPassRepo.save(visitorsPass);

        return GenerateOtpMapper.mapToResponse(foundCode, visitorsPass);
    }

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

        validateLoginDetails(estateSecurityLoginRequest);

        EstateSecurity foundEstateSecurity = estateSecurityRepository.findByEmail(estateSecurityLoginRequest.getEmail());
        boolean isSuccessful = PasswordHashingService.checkPassword(foundEstateSecurity.getPassword(), (estateSecurityLoginRequest.getPassword()));
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

    private void validateDetails(VisitorsPassRequest visitorsPassRequest){
        if (visitorsPassRequest.getOtpCode().isEmpty() || visitorsPassRequest.getOtpCode().isBlank()){
            throw new IllegalArgumentException("OtpCode can not be Empty");
        }

        if (visitorsPassRequest.getName().isEmpty() || visitorsPassRequest.getName().isBlank()){
            throw new IllegalArgumentException("Name can not be Empty");
        }

        if (visitorsPassRequest.getPhoneNumber().isEmpty() || visitorsPassRequest.getPhoneNumber().isBlank()){
            throw new IllegalArgumentException("PhoneNumber can not be Empty");
        }
    }
    private void validateLoginDetails(EstateSecurityLoginRequest estateSecurityLoginRequest){
        if (estateSecurityLoginRequest.getEmail().isEmpty() || estateSecurityLoginRequest.getEmail().isBlank()){
            throw new IllegalArgumentException("Space can not be Empty");
        }

        if (estateSecurityLoginRequest.getPassword().isEmpty() || estateSecurityLoginRequest.getPassword().isBlank()){
            throw new IllegalArgumentException("Space can not be Empty");
        }
    }
}
