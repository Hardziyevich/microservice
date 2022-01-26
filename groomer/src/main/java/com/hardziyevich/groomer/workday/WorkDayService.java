package com.hardziyevich.groomer.workday;


import com.hardziyevich.resource.dto.WorkingDayDto;

import java.util.List;

public interface WorkDayService {

    List<String> showAllWorkingDay();
    List<String> showAllWorkingDayByService(String serviceType);
    List<String> showAllWorkingDayByGroomerId(String groomerId);
    List<String> showAllWorkingDayByGroomerIdAndService(WorkingDayDto dto);
    List<Long> showALlGroomerIdByWorkingDay(String day);
    List<Long> showAllGroomerIdByWorkingDayAndService(String day, String serviceType);
}
