package com.hardziyevich.resource.dto;

import com.hardziyevich.resource.validation.DateValidation;
import com.hardziyevich.resource.validation.DigitValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDto {

    @DigitValidation
    private String groomerId;

    @DateValidation
    private String day;

}
