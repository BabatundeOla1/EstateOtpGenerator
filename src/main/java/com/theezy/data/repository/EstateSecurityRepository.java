package com.theezy.data.repository;

import com.theezy.data.models.EstateSecurity;
import com.theezy.data.models.Tenant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EstateSecurityRepository extends MongoRepository<EstateSecurity, String> {
    boolean existsByEmail(String email);
    EstateSecurity findByEmail(String email);
}
