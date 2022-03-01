package com.hardziyevich.gateway.servicetype;

import com.hardziyevich.resource.validation.DateValidation;

import javax.validation.constraints.NotNull;

public record ServiceDto(@NotNull String groomer, @DateValidation String day) {
}
