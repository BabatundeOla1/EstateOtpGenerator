package com.theezy.services;

import com.theezy.data.models.GenerateOTP;
import com.theezy.data.repository.ApartmentRepository;
import com.theezy.data.repository.GenerateOTPRepo;
import com.theezy.data.repository.TenantRepository;
import com.theezy.data.repository.UserRepository;
import com.theezy.dtos.request.ApartmentRegisterRequest;
import com.theezy.dtos.request.TenantLoginRequest;
import com.theezy.dtos.request.TenantRequest;
import com.theezy.dtos.response.TenantLoginResponse;
import com.theezy.exception.UserAlreadyExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TenantServiceImplTest {

    @Autowired
    private TenantServices tenantService;
    @Autowired
    private TenantRepository tenantRepository;
    @Autowired
    private GenerateOTPService generateOTPService;
    @Autowired
    private GenerateOTPRepo generateOTPRepo;
    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private ApartmentService apartmentService;
    @Autowired
    private UserRepository userRepository;

    public void setUpTenantLogin(TenantLoginRequest tenantLoginRequest){
        tenantLoginRequest.setEmail("BabatundeOla@gmail.com");
        tenantLoginRequest.setPassword("Password");
    }
    public void setUp(TenantRequest tenant){
//        tenant.setRoomId("A25");
        tenant.setName("Babatunde Olaleye");
        tenant.setEmail("BabatundeOla@gmail.com");
        tenant.setPassword("Password");
    }
    @BeforeEach
    public void clearRepositoryBeforeEachTest(){
        apartmentRepository.deleteAll();
        tenantRepository.deleteAll();
        generateOTPRepo.deleteAll();
    }
    @Test
    public void testThatRepositoryIsEmpty(){
        assertEquals(0, tenantRepository.count());
    }
    @Test
    public void testThatYouCanRegisterA_Tenant(){
        ApartmentRegisterRequest apartmentRegisterRequest = new ApartmentRegisterRequest();
        apartmentRegisterRequest.setHouseNumber("c5");
        apartmentService.registerApartment(apartmentRegisterRequest);
        assertEquals(1, apartmentRepository.count());

        TenantRequest tenant = new TenantRequest();
        tenant.setRoomId("c5");
        setUp(tenant);
        tenantService.registerTenant(tenant);
        assertEquals(1, tenantService.getNumberOfTenantInRepository());
        assertEquals(1, userRepository.count());
    }

    @Test
    public void testThatTenantCanNotRegisterTwice(){
        ApartmentRegisterRequest apartmentRegisterRequest = new ApartmentRegisterRequest();
        apartmentRegisterRequest.setHouseNumber("c5");
        apartmentService.registerApartment(apartmentRegisterRequest);
        assertEquals(1, apartmentRepository.count());

        TenantRequest tenant = new TenantRequest();
        tenant.setRoomId("c5");
        setUp(tenant);
        tenantService.registerTenant(tenant);
        assertEquals(1, tenantService.getNumberOfTenantInRepository());
        assertThrows(UserAlreadyExistException.class, ()-> tenantService.registerTenant(tenant));
    }
    @Test
    public void testThatTenantCanLogin(){
        ApartmentRegisterRequest apartmentRegisterRequest = new ApartmentRegisterRequest();
        apartmentRegisterRequest.setHouseNumber("c5");
        apartmentService.registerApartment(apartmentRegisterRequest);
        assertEquals(1, apartmentRepository.count());

        TenantRequest tenant = new TenantRequest();
        tenant.setRoomId("c5");
        setUp(tenant);
        tenantService.registerTenant(tenant);
        assertEquals(1, tenantService.getNumberOfTenantInRepository());

        TenantLoginRequest tenantLoginRequest = new TenantLoginRequest();
        setUpTenantLogin(tenantLoginRequest);
        TenantLoginResponse tenantLoginResponse = tenantService.tenantLogin(tenantLoginRequest);
        assertTrue(tenantLoginResponse.isSuccess());
    }
    @Test
    public void testThatErrorIsThrownWhenTenantLogin_WithAnInCorrectPassword(){
        ApartmentRegisterRequest apartmentRegisterRequest = new ApartmentRegisterRequest();
        apartmentRegisterRequest.setHouseNumber("c5");
        apartmentService.registerApartment(apartmentRegisterRequest);
        assertEquals(1, apartmentRepository.count());

        TenantRequest tenant = new TenantRequest();
        tenant.setRoomId("c5");
        setUp(tenant);
        tenantService.registerTenant(tenant);
        assertEquals(1, tenantService.getNumberOfTenantInRepository());

        TenantLoginRequest tenantLoginRequest = new TenantLoginRequest();
        tenantLoginRequest.setEmail("BabatundeOla@gmail.com");
        tenantLoginRequest.setPassword("tunde");
        assertThrows(IllegalArgumentException.class, ()-> tenantService.tenantLogin(tenantLoginRequest));
    }
    @Test
    public void testThatErrorIsThrownWhenTenantLogin_WithAnUnregisteredEmail(){
        ApartmentRegisterRequest apartmentRegisterRequest = new ApartmentRegisterRequest();
        apartmentRegisterRequest.setHouseNumber("c5");
        apartmentService.registerApartment(apartmentRegisterRequest);
        assertEquals(1, apartmentRepository.count());

        TenantRequest tenant = new TenantRequest();
        tenant.setRoomId("c5");
        setUp(tenant);
        tenantService.registerTenant(tenant);
        assertEquals(1, tenantService.getNumberOfTenantInRepository());

        TenantLoginRequest tenantLoginRequest = new TenantLoginRequest();
        tenantLoginRequest.setEmail("Unregistered@gmail.com");
        tenantLoginRequest.setPassword("Password");
        assertThrows(NullPointerException.class, ()-> tenantService.tenantLogin(tenantLoginRequest));
    }
    @Test
    public void testThatTenantCan_GenerateOTPCode(){
        ApartmentRegisterRequest apartmentRegisterRequest = new ApartmentRegisterRequest();
        apartmentRegisterRequest.setHouseNumber("c5");
        apartmentService.registerApartment(apartmentRegisterRequest);
        assertEquals(1, apartmentRepository.count());

        TenantRequest tenantRequest = new TenantRequest();
        tenantRequest.setRoomId("c5");
        setUp(tenantRequest);
        tenantService.registerTenant(tenantRequest);
        assertEquals(1, tenantService.getNumberOfTenantInRepository());

        TenantLoginRequest tenantLoginRequest = new TenantLoginRequest();
        setUpTenantLogin(tenantLoginRequest);
        TenantLoginResponse tenantLoginResponse = tenantService.tenantLogin(tenantLoginRequest);
        assertTrue(tenantLoginResponse.isSuccess());

        GenerateOTP otp = tenantService.generateOTP();

        assertEquals(1, generateOTPService.countCodeInOTPRepo());
    }
}
