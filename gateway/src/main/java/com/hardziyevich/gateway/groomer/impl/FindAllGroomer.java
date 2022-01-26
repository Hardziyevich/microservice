package com.hardziyevich.gateway.groomer.impl;

import com.hardziyevich.gateway.command.CommandProvider;
import com.hardziyevich.gateway.groomer.CommandRequestGroomer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FindAllGroomer implements CommandRequestGroomer {

    private final RestTemplate restTemplate;
    private final String requestUrl;
    private final String endpointGroomer;
    private final CommandProvider commandProvider = CommandProvider.FIND_ALL;

    public FindAllGroomer(RestTemplateBuilder builder,
                          @Value("${service.user.groomer.url}") String requestUrl,
                          @Value("${endpoint.groomer.all}") String endpointGroomer) {
        this.restTemplate = builder.build();
        this.requestUrl = requestUrl;
        this.endpointGroomer = endpointGroomer;
    }

    @Override
    public ResponseEntity<?> request() {
        return ResponseEntity.ok().body("");
    }

    @Override
    public CommandProvider showTypeRequest() {
        return commandProvider;
    }

    @Override
    public String defaultRequestUrl() {
        return requestUrl + endpointGroomer;
    }

    @Override
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

}
