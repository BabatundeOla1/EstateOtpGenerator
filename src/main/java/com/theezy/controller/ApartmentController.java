package com.theezy.controller;

import com.theezy.dtos.request.ApartmentRegisterRequest;
import com.theezy.dtos.response.ApartmentRegisterResponse;
import com.theezy.services.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apartment")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

//    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<ApartmentRegisterResponse> createApartment(ApartmentRegisterRequest apartmentRegisterRequest){
//        return new ResponseEntity<>(apartmentService.registerApartment(apartmentRegisterRequest), HttpStatus.OK);
//    }
}
