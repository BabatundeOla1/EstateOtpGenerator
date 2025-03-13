package com.theezy.utils;

import com.theezy.data.models.VisitorsPass;
import com.theezy.dtos.request.VisitorsPassRequest;

import java.time.LocalDateTime;

public class VisitorsPassMapper {

    public static VisitorsPass mapToRequest(VisitorsPassRequest visitorsPassRequest){
        VisitorsPass visitorsPass = new VisitorsPass();
        visitorsPass.setId(visitorsPassRequest.getId());
        visitorsPass.setName(visitorsPassRequest.getName());
        visitorsPass.setPhoneNumber(visitorsPassRequest.getPhoneNumber());
        visitorsPass.setTimeIn(LocalDateTime.now());
        return visitorsPass;
    }
}
