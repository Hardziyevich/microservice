package com.hardziyevich.gateway.command.impl;

import com.hardziyevich.gateway.command.CommandProvider;
import com.hardziyevich.gateway.command.Field;
import com.hardziyevich.gateway.command.Requester;
import com.hardziyevich.gateway.config.EndpointGroomerProperties;
import com.hardziyevich.gateway.config.ServiceProperties;
import com.hardziyevich.resource.dto.RequestToGroomerForWorkingDayDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public record FindDayAndService(RestTemplateBuilder builder,
                                ServiceProperties serviceProperties,
                                EndpointGroomerProperties endpointGroomerProperties) implements Requester {

    private static final CommandProvider commandProvider = CommandProvider.FIND_DAY_AND_SERVICE;

    @Override
    public ResponseEntity<Long[]> request() {
        RequestToGroomerForWorkingDayDto requestToGroomerForWorkingDayDto = new RequestToGroomerForWorkingDayDto(
                commandProvider.getValueFromField(Field.DAY),
                commandProvider.getValueFromField(Field.SERVICE)
        );
        return builder().build().postForEntity(serviceProperties.groomerDayUrl() + endpointGroomerProperties.byDayAndService(),
                requestToGroomerForWorkingDayDto, Long[].class);
    }

    @Override
    public CommandProvider showTypeRequest() {
        return commandProvider;
    }

    @Override
    public RestTemplate getRestTemplate() {
        return builder().build();
    }
}
