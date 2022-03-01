package com.hardziyevich.gateway.order;

import com.hardziyevich.resource.validation.DateValidation;
import com.hardziyevich.resource.validation.EmailAddress;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record OrderDto(
        @NotNull
        String groomer,
        @NotNull
        String serviceType,
        @DateValidation
        String day,
        @NotNull
        String timeType,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @EmailAddress
        String email,
        @NotBlank
        String petName
) {
}
