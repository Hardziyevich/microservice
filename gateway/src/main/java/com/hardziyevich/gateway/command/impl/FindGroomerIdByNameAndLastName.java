package com.hardziyevich.gateway.command.impl;

import com.hardziyevich.gateway.command.CommandProvider;
import com.hardziyevich.gateway.command.Field;
import com.hardziyevich.gateway.command.Requester;
import com.hardziyevich.gateway.config.EndpointUserProperties;
import com.hardziyevich.gateway.config.ServiceProperties;
import com.hardziyevich.resource.dto.PersonalDataGroomerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FindGroomerIdByNameAndLastName implements Requester {

    private final RestTemplate restTemplate;
    private final ServiceProperties serviceProperties;
    private final EndpointUserProperties endpointUserProperties;
    private final CommandProvider commandProvider = CommandProvider.FIND_GROOMER;

    public FindGroomerIdByNameAndLastName(RestTemplateBuilder builder,
                                          ServiceProperties serviceProperties,
                                          EndpointUserProperties endpointUserProperties) {
        this.restTemplate = builder.build();
        this.serviceProperties = serviceProperties;
        this.endpointUserProperties = endpointUserProperties;
    }

    @Override
    public ResponseEntity<Long> request() {
        String valueFromField = commandProvider.getValueFromField(Field.GROOMER);
        String[] initials = valueFromField.split(" ");
        String firstName = initials[0];
        String lastName = initials[1];
        PersonalDataGroomerDto personalDataGroomerDto = new PersonalDataGroomerDto(firstName, lastName);
        return restTemplate.postForEntity(serviceProperties.userGroomerUrl() + endpointUserProperties.findByNameAndLast(),
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
