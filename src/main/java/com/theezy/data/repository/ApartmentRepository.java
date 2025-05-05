package com.theezy.data.repository;

import com.theezy.data.models.Apartment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ApartmentRepository extends MongoRepository<Apartment, String> {

        Optional<Apartment> findApartmentByHouseNumber(String houseNumber);
        boolean existsByHouseNumber(String houseNumber);
}
