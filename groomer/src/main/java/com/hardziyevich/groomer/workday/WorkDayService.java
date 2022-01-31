package com.hardziyevich.groomer.workday;


import com.hardziyevich.resource.dto.RequestToGroomerForWorkingTimeDto;
import com.hardziyevich.resource.dto.ResponseFromGroomerForWorkingTimeDto;
import com.hardziyevich.resource.dto.WorkingDayDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WorkDayService {

    List<String> showAllWorkingDay();
    List<String> showAllWorkingDayByService(String serviceType);
    List<String> showAllWorkingDayByGroomerId(String groomerId);
    List<String> showAllWorkingDayByGroomerIdAndService(WorkingDayDto dto);
    List<Long> showALlGroomerIdByWorkingDay(String day);
    List<Long> showAllGroomerIdByWorkingDayAndService(String day, String serviceType);
    ResponseEntity<ResponseFromGroomerForWorkingTimeDto> findWorkingTime(RequestToGroomerForWorkingTimeDto dto);
}
