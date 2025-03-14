package com.theezy.services;

import com.theezy.data.models.VisitorsPass;
import com.theezy.data.repository.VisitorsPassRepo;
import com.theezy.dtos.request.VisitorsPassRequest;
import com.theezy.exception.VisitorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class VisitorsPassServiceImpl implements VisitorsPassService{

    @Autowired
    private VisitorsPassRepo visitorsPassRepo;
    @Override
    public VisitorsPass validateForCheckOut(VisitorsPassRequest visitorsPassRequest) {
        VisitorsPass foundVisitor = visitorsPassRepo.findVisitorsPassByName(visitorsPassRequest.getName())
                .orElseThrow(()-> new VisitorNotFoundException("Visitor not found"));

        foundVisitor.setValid(Boolean.FALSE);
        foundVisitor.setTimeOut(LocalDateTime.now());
        visitorsPassRepo.save(foundVisitor);
        return foundVisitor;
    }
}
