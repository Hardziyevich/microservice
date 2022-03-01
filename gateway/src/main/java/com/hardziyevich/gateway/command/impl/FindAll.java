package com.hardziyevich.gateway.command.impl;

import com.hardziyevich.gateway.command.CommandProvider;
import com.hardziyevich.gateway.command.CommandRequest;
import com.hardziyevich.gateway.command.Requester;
import com.hardziyevich.gateway.config.EndpointGroomerProperties;
import com.hardziyevich.gateway.config.ServiceProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FindAll implements CommandRequest {

    private final RestTemplate restTemplate;
    private final ServiceProperties serviceProperties;
    private final EndpointGroomerProperties endpointGroomer;
    private final CommandProvider commandProvider = CommandProvider.FIND_ALL;

    public FindAll(RestTemplateBuilder builder,
                   ServiceProperties serviceProperties,
                   EndpointGroomerProperties endpointGroomer) {
        this.restTemplate = builder.build();
        this.serviceProperties = serviceProperties;
        this.endpointGroomer = endpointGroomer;
    }

    @Override
    public ResponseEntity<String[]> request() {
        return restTemplate.postForEntity(serviceProperties.userGroomerUrl() + endpointGroomer.all(),
                "", String[].class);
    }

    @Override
    public CommandProvider showTypeRequest() {
        return commandProvider;
    }

    @Override
    public void setRequester(Requester requester) {

    }
}
