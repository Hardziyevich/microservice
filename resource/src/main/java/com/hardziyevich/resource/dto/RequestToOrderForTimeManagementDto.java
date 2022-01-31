package com.hardziyevich.resource.dto;

import com.hardziyevich.resource.validation.DateValidation;
import com.hardziyevich.resource.validation.DigitValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestToOrderForTimeManagementDto {

    @DigitValidation
    private String groomerId;

    @DateValidation
    private String day;

}
