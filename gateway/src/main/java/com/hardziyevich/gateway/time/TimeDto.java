package com.hardziyevich.gateway.time;

import com.hardziyevich.resource.validation.DateValidation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record TimeDto(@NotBlank String groomer, @NotNull String serviceType, @DateValidation String day) {
}
