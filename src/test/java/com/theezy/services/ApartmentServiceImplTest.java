package com.theezy.services;

import com.theezy.data.repository.ApartmentRepository;
import com.theezy.data.repository.TenantRepository;
import com.theezy.dtos.request.ApartmentRegisterRequest;
import com.theezy.dtos.request.TenantRequest;
import com.theezy.exception.ApartmentOccupiedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
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

    @BeforeEach
    public void clearRepositories(){
        tenantRepository.deleteAll();
        apartmentRepository.deleteAll();
    }
    public void setTenantUp(TenantRequest tenant, String apartmentNumber){
        tenant.setRoomId(apartmentNumber);
        tenant.setName("Babatunde Olaleye");
        tenant.setEmail("BabatundeOla@gmail.com");
        tenant.setPassword("Password");
    }
    public void setUpSecondTenant(TenantRequest tenant, String apartmentNumber){
        tenant.setRoomId(apartmentNumber);
        tenant.setName("Theezy");
        tenant.setEmail("theezy@gmail.com");
        tenant.setPassword("Password");
    }

    @Test
    public void testThatApartmentCanBeRegistered(){
        ApartmentRegisterRequest apartmentRegisterRequest = new ApartmentRegisterRequest();
        apartmentRegisterRequest.setHouseNumber("h25");
        apartmentService.registerApartment(apartmentRegisterRequest);
        assertEquals(1, apartmentRepository.count());
    }

    @Test
    public void testThatYouCanAddMultipleApartments(){
        ApartmentRegisterRequest firstApartment = new ApartmentRegisterRequest();
        firstApartment.setHouseNumber("A25");

        ApartmentRegisterRequest secondApartment = new ApartmentRegisterRequest();
        secondApartment.setHouseNumber("A27");

        apartmentService.registerApartment(firstApartment);
        apartmentService.registerApartment(secondApartment);
        assertEquals(2, apartmentRepository.count());
        System.out.println("List of available apartment: " + apartmentRepository.findAll());
    }

    @Test
    public void testThatTenantCanRegisterToAndExistingHouseNumber(){
        ApartmentRegisterRequest firstApartment = new ApartmentRegisterRequest();
        firstApartment.setHouseNumber("A27");
        apartmentService.registerApartment(firstApartment);
        assertEquals(1, apartmentRepository.count());

        TenantRequest newTenant = new TenantRequest();
        setTenantUp(newTenant, firstApartment.getHouseNumber());
        System.out.println("Tenant room id: " + newTenant.getRoomId());
        tenantServices.registerTenant(newTenant);
        assertEquals(1, tenantRepository.count());
    }

    @Test
    public void testThatErrorIsThrownWhen_TwoTenantRegisterOneApartment(){
        ApartmentRegisterRequest firstApartment = new ApartmentRegisterRequest();
        firstApartment.setHouseNumber("A27");
        apartmentService.registerApartment(firstApartment);
        assertEquals(1, apartmentRepository.count());

        TenantRequest newTenant = new TenantRequest();
        setTenantUp(newTenant, firstApartment.getHouseNumber());
        tenantServices.registerTenant(newTenant);
        assertEquals(1, tenantRepository.count());

        TenantRequest secondTenant = new TenantRequest();
        setUpSecondTenant(secondTenant, firstApartment.getHouseNumber());
        assertThrows(ApartmentOccupiedException.class, ()-> tenantServices.registerTenant(secondTenant));
    }
}