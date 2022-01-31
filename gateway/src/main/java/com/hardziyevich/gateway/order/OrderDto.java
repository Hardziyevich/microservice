package com.hardziyevich.gateway.order;

import com.hardziyevich.resource.validation.DateValidation;
import com.hardziyevich.resource.validation.EmailAddress;
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
public class OrderDto {

    @NotNull
    private String groomer;
    @NotNull
    private String serviceType;
    @DateValidation
    private String day;
    @NotNull
    private String timeType;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @EmailAddress
    private String email;
    @NotBlank
    private String petName;

}
