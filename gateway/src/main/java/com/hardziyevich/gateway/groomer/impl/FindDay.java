package com.hardziyevich.gateway.groomer.impl;

import com.hardziyevich.gateway.command.CommandProvider;
import com.hardziyevich.gateway.command.Field;
import com.hardziyevich.gateway.groomer.Requester;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FindDay implements Requester {
    private final RestTemplate restTemplate;
    private final String requestUrl;
    private final String endpoint;
    private final CommandProvider commandProvider = CommandProvider.FIND_DAY;

    public FindDay(RestTemplateBuilder builder,
                               @Value("${service.groomer.day.url}") String requestUrlDay,
                               @Value("${endpoint.groomer.by.day}") String endpointDay) {
        this.restTemplate = builder.build();
        this.requestUrl = requestUrlDay;
        this.endpoint = endpointDay;
    }

    @Override
    public ResponseEntity<?> request() {
        return restTemplate.postForEntity(requestUrl + endpoint,
                commandProvider.getValueFromField(Field.DAY), Long[].class);
    }

}
