package com.hardziyevich.user.entity;

import java.util.Arrays;

public enum Role {

    USER,
    GROOMER;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public Role findRole(String role){
        return Arrays.stream(Role.values())
                .filter(r -> r.toString().equals(role))
                .findFirst()
                .orElse(USER);
    }
}
