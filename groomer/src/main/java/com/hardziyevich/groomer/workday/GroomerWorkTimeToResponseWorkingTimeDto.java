package com.hardziyevich.groomer.workday;

import com.hardziyevich.groomer.entity.GroomerWorkTime;
import com.hardziyevich.resource.dto.ResponseFromGroomerForWorkingTimeDto;
import com.hardziyevich.resource.mapper.Mapper;

public class GroomerWorkTimeToResponseWorkingTimeDto implements Mapper<ResponseFromGroomerForWorkingTimeDto, GroomerWorkTime> {

    @Override
    public ResponseFromGroomerForWorkingTimeDto mapTo(GroomerWorkTime groomerWorkTime) {
        return ResponseFromGroomerForWorkingTimeDto.builder()
                .startWork(groomerWorkTime.getStartWork())
                .endWork(groomerWorkTime.getEndWork())
                .duration(groomerWorkTime.getServiceTypes().getDuration())
                .build();
    }

}
