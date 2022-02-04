package com.hardziyevich.gateway.command.impl;

import com.hardziyevich.gateway.command.CommandProvider;
import com.hardziyevich.gateway.command.Field;
import com.hardziyevich.gateway.command.Requester;
import com.hardziyevich.resource.dto.PersonalDataGroomerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FindGroomerIdByNameAndLastName implements Requester {

    private final RestTemplate restTemplate;
    private final String requestUrl;
    private final String endpoint;
    private final CommandProvider commandProvider = CommandProvider.FIND_GROOMER;

    public FindGroomerIdByNameAndLastName(RestTemplateBuilder builder,
                       @Value("${service.user.groomer.url}") String requestUrl,
                       @Value("${endpoint.user.find.by.name.and.last}") String endpoint) {
        this.restTemplate = builder.build();
        this.requestUrl = requestUrl;
        this.endpoint = endpoint;
    }

    @Override
    public ResponseEntity<Long> request() {
        String valueFromField = commandProvider.getValueFromField(Field.GROOMER);
        String[] initials = valueFromField.split(" ");
        String firstName = initials[0];
        String lastName = initials[1];
        PersonalDataGroomerDto personalDataGroomerDto = new PersonalDataGroomerDto(firstName,lastName);
        return restTemplate.postForEntity(requestUrl + endpoint,
                personalDataGroomerDto, Long.class);
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
