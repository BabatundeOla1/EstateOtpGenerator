package com.theezy.dtos.response;

import lombok.Data;

@Data
public class ApartmentRegisterResponse {

    private boolean status;
    private Object data;
    private String message;
}
