package com.hardziyevich.resource.dto;

import com.hardziyevich.resource.validation.DateValidation;
import com.hardziyevich.resource.validation.DigitValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestWorkingTimeDto {

    @DateValidation
    private String day;

    @NotNull
    private String serviceType;

    @NotNull
    private String groomerId;

}
