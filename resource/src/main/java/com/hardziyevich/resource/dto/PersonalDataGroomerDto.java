package com.hardziyevich.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalDataGroomerDto {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

}
