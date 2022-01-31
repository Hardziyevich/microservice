package com.hardziyevich.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestToOrderForRegistrationOrderDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long groomerId;

    @NotNull
    private String petName;

    @NotNull
    private String service;

    @NotNull
    private LocalDate day;

    @NotNull
    private LocalTime time;

    @NotNull
    private LocalTime duration;

}
