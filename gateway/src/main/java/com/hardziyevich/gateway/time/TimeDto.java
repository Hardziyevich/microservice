package com.hardziyevich.gateway.time;

import com.hardziyevich.resource.validation.DateValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeDto {

    @NotBlank
    private String groomer;

    @NotNull
    private String serviceType;

    @DateValidation
    private String day;
}
