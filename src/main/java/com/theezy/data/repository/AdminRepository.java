package com.theezy.data.repository;

import com.theezy.data.models.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepository extends MongoRepository<Admin, String> {
    Optional<Admin> findAdminByEmail(String email);
//    Optional<Admin> findAdminByEmailIgnoreCase(String email);
    boolean existsByEmail(String email);
}
