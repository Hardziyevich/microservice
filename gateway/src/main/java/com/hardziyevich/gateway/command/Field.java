package com.hardziyevich.gateway.command;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum Field {
    GROOMER,
    DAY,
    SERVICE;

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

    public static List<Field> findAllNotBlankFields() {
        return Arrays.stream(Field.values())
                .filter(f -> Optional.ofNullable(f.getValue()).isPresent())
                .filter(f -> !f.getValue().isBlank())
                .toList();
    }
}
