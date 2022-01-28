package com.hardziyevich.groomer.workday;

import com.hardziyevich.groomer.entity.GroomerWorkTime;
import com.hardziyevich.resource.dto.ResponseWorkingTimeDto;
import com.hardziyevich.resource.mapper.Mapper;

public class GroomerWorkTimeToResponseWorkingTimeDto implements Mapper<ResponseWorkingTimeDto, GroomerWorkTime> {

    @Override
    public ResponseWorkingTimeDto mapTo(GroomerWorkTime groomerWorkTime) {
        return ResponseWorkingTimeDto.builder()
                .startWork(groomerWorkTime.getStartWork())
                .endWork(groomerWorkTime.getEndWork())
                .build();
    }

}
