package com.hardziyevich.gateway.groomer.impl;

import com.hardziyevich.gateway.command.CommandProvider;
import com.hardziyevich.gateway.command.CommandRequest;
import com.hardziyevich.gateway.command.Requester;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FindAll implements CommandRequest {

    private final RestTemplate restTemplate;
    private final String requestUrl;
    private final String endpointGroomer;
    private final CommandProvider commandProvider = CommandProvider.FIND_ALL;

    public FindAll(RestTemplateBuilder builder,
                   @Value("${service.user.groomer.url}") String requestUrl,
                   @Value("${endpoint.groomer.all}") String endpointGroomer) {
        this.restTemplate = builder.build();
        this.requestUrl = requestUrl;
        this.endpointGroomer = endpointGroomer;
    }

    @Override
    public ResponseEntity<?> request() {
        return restTemplate.postForEntity(requestUrl + endpointGroomer,
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
