package com.theezy.data.models;

import lombok.Data;

@Data
public class Apartment {
    private String id;
    private String houseNumber;
    private String ownerEmail;
    private boolean isOccupied;
}
