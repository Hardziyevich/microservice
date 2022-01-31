package com.hardziyevich.groomer.servicetype;

import com.hardziyevich.resource.dto.RequestToGroomerForServiceDto;

import java.util.List;

public interface TypeService {

    List<String> showAllService();
    List<String> showAllServiceByDay(String day);
    List<String> showAllServiceByGroomerId(String groomerId);
    List<String> showAllServiceByGroomerIdAndDay(RequestToGroomerForServiceDto dto);
    List<Long> showALlGroomerIdByService(String service);
}
