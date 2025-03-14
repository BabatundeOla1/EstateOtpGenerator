package com.theezy.data.repository;

import com.theezy.data.models.VisitorsPass;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VisitorsPassRepo extends MongoRepository<VisitorsPass, String> {
    Optional<VisitorsPass> findVisitorsPassByName(String name);

    boolean existsByName(String name);

}
