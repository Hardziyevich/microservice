package com.hardziyevich.gateway.time;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class PeriodOrder {

    private LocalTime start;
    private LocalTime end;

}
