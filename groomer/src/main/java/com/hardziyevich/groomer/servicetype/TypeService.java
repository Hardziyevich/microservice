package com.hardziyevich.groomer.servicetype;

import com.hardziyevich.resource.dto.ServiceDto;

import java.util.List;

public interface TypeService {

    List<String> showAllService();
    List<String> showAllServiceByDay(String day);
    List<String> showAllServiceByGroomerId(String groomerId);
    List<String> showAllServiceByGroomerIdAndDay(ServiceDto dto);
}
