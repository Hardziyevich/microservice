package com.hardziyevich.resource.dto;

import com.hardziyevich.resource.validation.EmailAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @EmailAddress
    private String email;

    private String password;

}
