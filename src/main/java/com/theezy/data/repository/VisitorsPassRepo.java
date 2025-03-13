package com.theezy.data.repository;

import com.theezy.data.models.VisitorsPass;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VisitorsPassRepo extends MongoRepository<VisitorsPass, String> {
    VisitorsPass findVisitorsPassByPhoneNumber(String phoneNumber);

    VisitorsPass findVisitorsPassById(String visitorPassId);
}
