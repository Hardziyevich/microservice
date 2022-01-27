package com.hardziyevich.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderTimeManagementDto {

    private LocalDate day;
    private LocalTime time;
    private LocalTime duration;

}
