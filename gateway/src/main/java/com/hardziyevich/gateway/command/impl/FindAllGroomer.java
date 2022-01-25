package com.hardziyevich.gateway.command.impl;

import com.hardziyevich.gateway.command.CommandProvider;
import com.hardziyevich.gateway.command.CommandRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FindAllGroomer implements CommandRequest {

    private static final String ENDPOINT = "/search";

    private final RestTemplate restTemplate;
    private final String requestUrl;
    private final CommandProvider commandProvider = CommandProvider.FIND_ALL;

    public FindAllGroomer(RestTemplateBuilder builder, @Value("${service.user.groomer.url}") String requestUrl) {
        this.restTemplate = builder.build();
        this.requestUrl = requestUrl;
    }

    @Override
    public ResponseEntity<?> request() {
        return restTemplate.getForEntity(requestUrl + ENDPOINT, String[].class);
    }

    @Override
    public CommandProvider showTypeRequest() {
        return commandProvider;
    }
}
