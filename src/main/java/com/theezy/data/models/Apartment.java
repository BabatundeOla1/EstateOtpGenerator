package com.theezy.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Apartment {
    @Id
    private String id;
    private String houseNumber;
    private String ownerEmail;
    private boolean isOccupied;
}
