package com.hardziyevich.gateway.groomer.impl;

import com.hardziyevich.gateway.command.CommandProvider;
import com.hardziyevich.gateway.command.Field;
import com.hardziyevich.gateway.command.Requester;
import com.hardziyevich.resource.dto.GroomerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FindDayAndService implements Requester {

    private final RestTemplate restTemplate;
    private final String requestUrl;
    private final String endpoint;
    private final CommandProvider commandProvider = CommandProvider.FIND_DAY_AND_SERVICE;

    public FindDayAndService(RestTemplateBuilder builder,
                                         @Value("${service.groomer.day.url}") String requestUrlDay,
                                         @Value("${endpoint.groomer.by.day.and.service}") String endpointDay) {
        this.restTemplate = builder.build();
        this.requestUrl = requestUrlDay;
        this.endpoint = endpointDay;
    }

    @Override
    public ResponseEntity<?> request() {
        GroomerDto groomerDto = new GroomerDto(
                commandProvider.getValueFromField(Field.DAY),
                commandProvider.getValueFromField(Field.SERVICE)
        );
        return restTemplate.postForEntity(requestUrl + endpoint,
                groomerDto, Long[].class);
    }

    @Override
    public CommandProvider showTypeRequest() {
        return commandProvider;
    }

    @Override
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
