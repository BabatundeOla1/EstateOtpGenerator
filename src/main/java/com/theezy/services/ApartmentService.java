package com.theezy.services;

import com.theezy.data.models.Apartment;
import com.theezy.dtos.request.ApartmentRegisterRequest;
import com.theezy.dtos.response.ApartmentRegisterResponse;
import com.theezy.dtos.response.DeleteApartmentResponse;

import java.util.List;

public interface ApartmentService {
    ApartmentRegisterResponse registerApartment(ApartmentRegisterRequest apartmentRegisterRequest);

    List<Apartment> viewAllApartment();

    DeleteApartmentResponse deleteApartment(String houseNumber);
}
