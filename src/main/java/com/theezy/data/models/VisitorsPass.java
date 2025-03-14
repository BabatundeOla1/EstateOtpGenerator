package com.theezy.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;


@Data
public class VisitorsPass {

    @Id
    private String Id;
    private String name;
    private String phoneNumber;
    private String otpCode;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
    private boolean isValid;
}
