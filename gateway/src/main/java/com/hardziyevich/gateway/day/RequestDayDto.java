package com.hardziyevich.gateway.day;

import com.hardziyevich.resource.validation.DigitValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDayDto {

    @NotNull
    private String groomer;

    @NotNull
    private String serviceType;

}
