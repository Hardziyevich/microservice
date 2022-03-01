package com.hardziyevich.gateway.command.impl;

import com.hardziyevich.gateway.command.CommandProvider;
import com.hardziyevich.gateway.command.Field;
import com.hardziyevich.gateway.command.Requester;
import com.hardziyevich.gateway.config.EndpointGroomerProperties;
import com.hardziyevich.gateway.config.ServiceProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FindService implements Requester {

    private final RestTemplate restTemplate;
    private final ServiceProperties serviceProperties;
    private final EndpointGroomerProperties endpointGroomerProperties;
    private final CommandProvider commandProvider = CommandProvider.FIND_SERVICE;

    public FindService(RestTemplateBuilder builder,
                       ServiceProperties serviceProperties,
                       EndpointGroomerProperties endpointGroomerProperties) {
        this.restTemplate = builder.build();
        this.serviceProperties = serviceProperties;
        this.endpointGroomerProperties = endpointGroomerProperties;
    }

    @Override
    public ResponseEntity<Long[]> request() {
        return restTemplate.postForEntity(serviceProperties.groomerServiceUrl() + endpointGroomerProperties.byService(),
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
