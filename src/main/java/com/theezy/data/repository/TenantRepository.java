package com.theezy.data.repository;

import com.theezy.data.models.Tenant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TenantRepository extends MongoRepository<Tenant, String> {
    boolean existsTenantByEmail(String email);
    Tenant findTenantByEmail(String email);
}
