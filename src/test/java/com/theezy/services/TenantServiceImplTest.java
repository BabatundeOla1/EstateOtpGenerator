package com.theezy.services;

import com.theezy.data.repository.TenantRepository;
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

        public void setUpTenantLogin(TenantLoginRequest tenantLoginRequest){
            tenantLoginRequest.setEmail("BabatundeOla@gmail.com");
            tenantLoginRequest.setPassword("Password");
        }
        public void setUp(TenantRequest tenant){
            tenant.setRoomId("TH91");
            tenant.setName("Babatunde Olaleye");
            tenant.setEmail("BabatundeOla@gmail.com");
            tenant.setPassword("Password");
        }
        @BeforeEach
        public void clearRepositoryAfterEachTest(){
            tenantRepository.deleteAll();
        }
        @Test
        public void testThatRepositoryIsEmpty(){
            assertEquals(0, tenantRepository.count());
        }
        @Test
        public void testThatYouCanRegisterA_Tenant(){
            TenantRequest tenant = new TenantRequest();
            setUp(tenant);
            tenantService.registerTenant(tenant);
            assertEquals(1, tenantService.getNumberOfTenantInRepository());
        }
        @Test
        public void testThatTenantCanNotRegisterTwice(){
            TenantRequest tenant = new TenantRequest();
            setUp(tenant);
            tenantService.registerTenant(tenant);
            assertEquals(1, tenantService.getNumberOfTenantInRepository());
            assertThrows(UserAlreadyExistException.class, ()-> tenantService.registerTenant(tenant));
        }

        @Test
        public void testThatTenantCanLogin(){
            TenantRequest tenant = new TenantRequest();
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
            TenantRequest tenant = new TenantRequest();
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
            TenantRequest tenant = new TenantRequest();
            setUp(tenant);
            tenantService.registerTenant(tenant);
            assertEquals(1, tenantService.getNumberOfTenantInRepository());

            TenantLoginRequest tenantLoginRequest = new TenantLoginRequest();
            tenantLoginRequest.setEmail("Unregistered@gmail.com");
            tenantLoginRequest.setPassword("Password");
            assertThrows(NullPointerException.class, ()-> tenantService.tenantLogin(tenantLoginRequest));
        }
}