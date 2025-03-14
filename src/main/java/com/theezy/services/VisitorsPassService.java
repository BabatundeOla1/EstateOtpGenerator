package com.theezy.services;

import com.theezy.data.models.VisitorsPass;
import com.theezy.dtos.request.VisitorsPassRequest;

public interface VisitorsPassService {

    VisitorsPass validateForCheckOut(VisitorsPassRequest visitorsPassRequest);
}
