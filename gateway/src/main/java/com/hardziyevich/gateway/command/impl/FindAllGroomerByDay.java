package com.hardziyevich.gateway.command.impl;

import com.hardziyevich.gateway.command.CommandProvider;
import com.hardziyevich.gateway.command.CommandRequest;
import com.hardziyevich.gateway.command.Field;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

import static com.hardziyevich.gateway.command.CommandProvider.FIND_DAY;

@Component
public class FindAllGroomerByDay implements CommandRequest {

    private final RestTemplate restTemplate;
    private final String requestUrlGroomer;
    private final String requestUrlDay;
    private final String endpointDay;
    private final String endpointGroomer;

    private CommandProvider commandProvider = FIND_DAY;

    public FindAllGroomerByDay(RestTemplateBuilder builder,
                               @Value("${service.user.groomer.url}") String requestUrlGroomer,
                               @Value("${service.groomer.day.url}") String requestUrlDay,
                               @Value("${endpoint.groomer.by.day}") String endpointDay,
                               @Value("${endpoint.user.find.by.id}") String endpointGroomer) {
        this.restTemplate = builder.build();
        this.requestUrlGroomer = requestUrlGroomer;
        this.requestUrlDay = requestUrlDay;
        this.endpointDay = endpointDay;
        this.endpointGroomer = endpointGroomer;
    }

    @Override
    public ResponseEntity<?> request() {
        ResponseEntity<?> result;
        ResponseEntity<Long[]> responseEntity = restTemplate.postForEntity(requestUrlDay + endpointDay,
                commandProvider.getValueFromField(Field.DAY), Long[].class);
        HttpStatus statusCode = responseEntity.getStatusCode();
        if (statusCode.is2xxSuccessful()) {
            Long[] body = responseEntity.getBody();
            Long[] request = Optional.ofNullable(body).orElse(new Long[]{});
            result = restTemplate.postForEntity(requestUrlGroomer + endpointGroomer,
                    Arrays.asList(request), String[].class);
        } else {
            result = responseEntity;
        }
        return result;
    }

    @Override
    public CommandProvider showTypeRequest() {
        return commandProvider;
    }

}
