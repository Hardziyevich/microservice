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
public class FindAllGroomerByService implements CommandRequestGroomer {

    private final RestTemplate restTemplate;
    private final String requestUrlGroomer;
    private final String requestUrl;
    private final String endpoint;
    private final String endpointGroomer;
    private final CommandProvider commandProvider = CommandProvider.FIND_SERVICE;

    public FindAllGroomerByService(RestTemplateBuilder builder,
                               @Value("${service.user.groomer.url}") String requestUrlGroomer,
                               @Value("${service.groomer.service.url}") String requestUrlService,
                               @Value("${endpoint.groomer.by.service}") String endpointService,
                               @Value("${endpoint.user.find.by.id}") String endpointGroomer) {
        this.restTemplate = builder.build();
        this.requestUrlGroomer = requestUrlGroomer;
        this.requestUrl = requestUrlService;
        this.endpoint = endpointService;
        this.endpointGroomer = endpointGroomer;
    }

    @Override
    public ResponseEntity<?> request() {
        return restTemplate.postForEntity(requestUrl + endpoint,
                commandProvider.getValueFromField(Field.SERVICE), Long[].class);
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
