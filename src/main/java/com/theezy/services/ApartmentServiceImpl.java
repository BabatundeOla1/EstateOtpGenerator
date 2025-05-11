package com.theezy.services;

import com.theezy.data.models.Apartment;
import com.theezy.data.repository.ApartmentRepository;
import com.theezy.dtos.request.ApartmentRegisterRequest;
import com.theezy.dtos.response.ApartmentRegisterResponse;
import com.theezy.dtos.response.DeleteApartmentResponse;
import com.theezy.exception.ApartmentAlreadyExistException;
import com.theezy.exception.ApartmentNotFoundException;
import com.theezy.utils.ApartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentServiceImpl implements ApartmentService{

    @Autowired
    private ApartmentRepository apartmentRepository;
    @Override
    public ApartmentRegisterResponse registerApartment(ApartmentRegisterRequest apartmentRegisterRequest) {
        System.out.println("Received request to create apartment in service: " + apartmentRegisterRequest);
        boolean existingApartment = apartmentRepository.existsByHouseNumber(apartmentRegisterRequest.getHouseNumber());

        if (existingApartment){
            throw new ApartmentAlreadyExistException("Apartment already exist");
        }

        Apartment registeredApartment = ApartmentMapper.mapRequestToApartment(apartmentRegisterRequest);
        apartmentRepository.save(registeredApartment);
        return ApartmentMapper.mapApartmentToResponse(registeredApartment);
    }

    @Override
    public List<Apartment> viewAllApartment() {
        return apartmentRepository.findAll();
    }

    @Override
    public DeleteApartmentResponse deleteApartment(String houseNumber) {
        Apartment foundApartment = apartmentRepository.findApartmentByHouseNumber(houseNumber)
                        .orElseThrow(()-> new ApartmentNotFoundException("Apartment Not Found"));
        apartmentRepository.delete(foundApartment);
        return ApartmentMapper.mapApartmentToDelete("Apartment deleted!");
    }
}
