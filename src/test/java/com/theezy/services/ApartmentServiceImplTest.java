package com.theezy.services;

import com.theezy.data.repository.ApartmentRepository;
import com.theezy.data.repository.TenantRepository;
import com.theezy.dtos.request.ApartmentRegisterRequest;
import com.theezy.dtos.request.TenantRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApartmentServiceImplTest {
    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private ApartmentService apartmentService;
    @Autowired
    private TenantRepository tenantRepository;
    @Autowired
    private TenantServices tenantServices;

    public void setUp(TenantRequest tenant){
        tenant.setRoomId("h25");
        tenant.setName("Babatunde Olaleye");
        tenant.setEmail("BabatundeOla@gmail.com");
        tenant.setPassword("Password");
    }
    @BeforeEach
    public void clearRepositoryAfterEachTest(){
        tenantRepository.deleteAll();
    }

    @BeforeEach
    public void clearApartmentRepository(){
        apartmentRepository.deleteAll();
    }
    @Test
    public void testThatApartmentCanBeRegistered(){
        ApartmentRegisterRequest apartmentRegisterRequest = new ApartmentRegisterRequest();
        apartmentRegisterRequest.setHouseNumber("h25");
        apartmentService.registerApartment(apartmentRegisterRequest);
        assertEquals(1, apartmentRepository.count());
    }
    @Test
    public void testThatUserCanRegisterWithRegisteredRoom(){
        TenantRequest tenant = new TenantRequest();
        setUp(tenant);
        tenantServices.registerTenant(tenant);
        assertEquals(1, tenantServices.getNumberOfTenantInRepository());
    }
}