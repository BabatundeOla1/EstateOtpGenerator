package com.theezy.controller;

import com.theezy.dtos.request.ApartmentRegisterRequest;
import com.theezy.dtos.response.ApartmentRegisterResponse;
import com.theezy.services.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/apartment")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @PostMapping("/admin/create-apartment")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApartmentRegisterResponse> createApartment(@RequestBody ApartmentRegisterRequest apartmentRegisterRequest){
        try {
            ApartmentRegisterResponse response = apartmentService.registerApartment(apartmentRegisterRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception error){
            System.out.println(error.getMessage());
            throw  error;
        }
    }
}
