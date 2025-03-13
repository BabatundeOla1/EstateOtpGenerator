package com.theezy.data.models;

import lombok.Data;


@Data
public class Tenant {
    private String email;
    private String name;
    private String roomId;
    private String password;
//    private String generateOTP;
}
