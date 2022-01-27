package com.hardziyevich.gateway.command.impl;

import com.hardziyevich.gateway.command.CommandProvider;
import com.hardziyevich.gateway.command.Field;
import com.hardziyevich.gateway.command.Requester;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FindService implements Requester<Long[]> {

    private final RestTemplate restTemplate;
    private final String requestUrl;
    private final String endpoint;
    private final CommandProvider commandProvider = CommandProvider.FIND_SERVICE;

    public FindService(RestTemplateBuilder builder,
                                   @Value("${service.groomer.service.url}") String requestUrlService,
                                   @Value("${endpoint.groomer.by.service}") String endpointService) {
        this.restTemplate = builder.build();
        this.requestUrl = requestUrlService;
        this.endpoint = endpointService;
    }

    @Override
    public ResponseEntity<Long[]> request() {
        return restTemplate.postForEntity(requestUrl + endpoint,
                commandProvider.getValueFromField(Field.SERVICE), Long[].class);
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
