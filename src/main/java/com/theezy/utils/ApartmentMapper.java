package com.theezy.utils;

import com.theezy.data.models.Apartment;
import com.theezy.dtos.request.ApartmentRegisterRequest;
import com.theezy.dtos.response.ApartmentRegisterResponse;
import com.theezy.dtos.response.DeleteApartmentResponse;

public class ApartmentMapper {

    public static Apartment mapRequestToApartment(ApartmentRegisterRequest apartmentRegisterRequest){
        Apartment apartment = new Apartment();
        apartment.setHouseNumber(apartmentRegisterRequest.getHouseNumber());
        apartment.setOccupied(false);
        return apartment;
    }

    public static ApartmentRegisterResponse mapApartmentToResponse(Apartment apartment){
        ApartmentRegisterResponse apartmentRegisterResponse = new ApartmentRegisterResponse();
        apartmentRegisterResponse.setMessage(apartment.getHouseNumber() + " Apartment Registered Successfully");
        apartmentRegisterResponse.setStatus(true);
        return apartmentRegisterResponse;
    }

    public static DeleteApartmentResponse mapApartmentToDelete(String message){
        DeleteApartmentResponse deleteApartmentResponse = new DeleteApartmentResponse();
        deleteApartmentResponse.setMessage(message);
        return deleteApartmentResponse;
    }
}
