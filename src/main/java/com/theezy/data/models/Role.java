package com.theezy.data.models;

public enum Role {
    SECURITY,
    TENANT,
    ADMIN;

    @Override
    public String toString() {
        return "ROLE_" + name();
    }
}
