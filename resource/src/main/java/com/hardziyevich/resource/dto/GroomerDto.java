package com.hardziyevich.resource.dto;

import com.hardziyevich.resource.validation.DateValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroomerDto {

    @DateValidation
    private String day;

    @NotNull
    private String serviceType;

}
