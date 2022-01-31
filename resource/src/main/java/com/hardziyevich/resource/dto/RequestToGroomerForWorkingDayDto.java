package com.hardziyevich.resource.dto;

import com.hardziyevich.resource.validation.DateValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestToGroomerForWorkingDayDto {

    @DateValidation
    private String day;

    @NotNull
    private String serviceType;

}
