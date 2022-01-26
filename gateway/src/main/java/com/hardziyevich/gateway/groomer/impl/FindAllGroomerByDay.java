package com.hardziyevich.gateway.groomer.impl;

import com.hardziyevich.gateway.command.CommandProvider;
import com.hardziyevich.gateway.command.Field;
import com.hardziyevich.gateway.groomer.CommandRequestGroomer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FindAllGroomerByDay implements CommandRequestGroomer {

    private final RestTemplate restTemplate;
    private final String requestUrlGroomer;
    private final String requestUrl;
    private final String endpoint;
    private final String endpointGroomer;
    private final CommandProvider commandProvider = CommandProvider.FIND_DAY;

    public FindAllGroomerByDay(RestTemplateBuilder builder,
                               @Value("${service.user.groomer.url}") String requestUrlGroomer,
                               @Value("${service.groomer.day.url}") String requestUrlDay,
                               @Value("${endpoint.groomer.by.day}") String endpointDay,
                               @Value("${endpoint.user.find.by.id}") String endpointGroomer) {
        this.restTemplate = builder.build();
        this.requestUrlGroomer = requestUrlGroomer;
        this.requestUrl = requestUrlDay;
        this.endpoint = endpointDay;
        this.endpointGroomer = endpointGroomer;
    }

    @Override
    public ResponseEntity<?> request() {
        return restTemplate.postForEntity(requestUrl + endpoint,
                commandProvider.getValueFromField(Field.DAY), Long[].class);
    }

    @Override
    public CommandProvider showTypeRequest() {
        return commandProvider;
    }

    @Override
    public String defaultRequestUrl() {
        return requestUrlGroomer + endpointGroomer;
    }

    @Override
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

}
