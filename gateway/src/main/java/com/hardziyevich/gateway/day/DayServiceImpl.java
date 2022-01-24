package com.hardziyevich.gateway.day;

import com.hardziyevich.resource.dto.ServiceDto;
import com.hardziyevich.resource.dto.WorkingDayDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class DayServiceImpl implements DayService{

    private static final String FIND = "/search";

    private final RestTemplate restTemplate;
    private final String dayUrl;

    public DayServiceImpl(RestTemplateBuilder builder, @Value("${service.groomer.day.url}") String dayUrl) {
        this.restTemplate = builder.build();
        this.dayUrl = dayUrl;
    }

    @Override
    public List<String> showAllDay(RequestDayDto requestDayDto) {
        boolean groomerIsBlank = requestDayDto.getGroomer().isBlank();
        ResponseEntity<String[]> listResponseEntity;
        if (groomerIsBlank) {
            listResponseEntity = restTemplate.postForEntity(dayUrl + FIND, WorkingDayDto.builder()
                    .serviceType(requestDayDto.getServiceType())
                    .groomerId(requestDayDto.getGroomer())
                    .build(), String[].class);
        } else {
            listResponseEntity = ResponseEntity.ok().build();
        }
        return Arrays.stream(Objects.requireNonNull(listResponseEntity.getBody())).toList();
    }

}
