package com.theezy.dtos.request;

import lombok.Data;

@Data

public class EstateSecurityRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String Id;
}
