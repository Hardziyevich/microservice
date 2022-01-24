package com.hardziyevich.gateway.servicetype;

import com.hardziyevich.resource.dto.ServiceDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class TypeServiceImpl implements TypeService {

    private static final String FIND = "/search";

    private final RestTemplate restTemplate;
    private final String serviceUrl;

    public TypeServiceImpl(RestTemplateBuilder builder,
                           @Value("${service.groomer.service.url}") String serviceUrl) {
        this.restTemplate = builder.build();
        this.serviceUrl = serviceUrl;
    }

    @Override
    public List<String> showAllService(RequestServiceDto requestServiceDto) {
        boolean groomerIsBlank = requestServiceDto.getGroomer().isBlank();
        ResponseEntity<String[]> listResponseEntity;
        if (groomerIsBlank) {
            listResponseEntity = restTemplate.postForEntity(serviceUrl + FIND, ServiceDto.builder()
                    .day(requestServiceDto.getDay())
                    .groomerId(requestServiceDto.getGroomer())
                    .build(), String[].class);
        } else {
            listResponseEntity = ResponseEntity.ok().build();
        }
        return Arrays.stream(Objects.requireNonNull(listResponseEntity.getBody())).toList();
    }
}
