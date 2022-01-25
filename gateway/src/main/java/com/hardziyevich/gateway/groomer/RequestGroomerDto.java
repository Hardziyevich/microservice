package com.hardziyevich.gateway.groomer;

import com.hardziyevich.resource.validation.DateValidation;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestGroomerDto {

    @DateValidation
    private String day;

    @NotNull
    private String serviceType;

}
