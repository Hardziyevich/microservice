package com.hardziyevich.gateway.servicetype;

import com.hardziyevich.resource.validation.DateValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDto {

    @NotNull
    private String groomer;

    @DateValidation
    private String day;

}
