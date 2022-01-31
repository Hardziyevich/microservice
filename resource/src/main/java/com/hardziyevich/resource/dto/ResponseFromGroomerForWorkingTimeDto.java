package com.hardziyevich.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFromGroomerForWorkingTimeDto {

    private LocalTime startWork;

    private LocalTime endWork;

    private LocalTime duration;

}
