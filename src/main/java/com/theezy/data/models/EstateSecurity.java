package com.theezy.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class EstateSecurity {
    @Id
    private String Id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
