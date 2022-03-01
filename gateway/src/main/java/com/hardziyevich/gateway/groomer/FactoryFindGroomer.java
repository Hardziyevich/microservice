package com.hardziyevich.gateway.groomer;

import com.hardziyevich.gateway.command.CommandProvider;
import com.hardziyevich.gateway.command.CommandRequest;
import com.hardziyevich.gateway.command.Requester;
import com.hardziyevich.gateway.command.impl.FindAllGroomerBy;
import com.hardziyevich.gateway.config.EndpointGroomerProperties;
import com.hardziyevich.gateway.config.EndpointUserProperties;
import com.hardziyevich.gateway.config.ServiceProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public record FactoryFindGroomer(ServiceProperties serviceProperties,
                                 EndpointUserProperties endpointUserProperties,
                                 EndpointGroomerProperties endpointGroomer,
                                 RestTemplateBuilder builder) {

    private static final String RESPONSE_FOR_FIND_ALL_GROOMER = "";

    @Bean
    public CommandRequest findAllGroomerByDay(@Qualifier("findDay") Requester requester) {
        CommandRequest commandRequest = new FindAllGroomerBy(serviceProperties.userGroomerUrl(), endpointUserProperties.findById());
        commandRequest.setRequester(requester);
        return commandRequest;
    }

    @Bean
    public CommandRequest findAllGroomerByService(@Qualifier("findService") Requester requester) {
        CommandRequest commandRequest = new FindAllGroomerBy(serviceProperties.userGroomerUrl(), endpointUserProperties.findById());
        commandRequest.setRequester(requester);
        return commandRequest;
    }

    @Bean
    public CommandRequest findAllGroomerByDayAndService(@Qualifier("findDayAndService") Requester requester) {
        CommandRequest commandRequest = new FindAllGroomerBy(serviceProperties.userGroomerUrl(), endpointUserProperties.findById());
        commandRequest.setRequester(requester);
        return commandRequest;
    }

    @Bean
    public CommandRequest findAllGroomer() {
        CommandRequest commandRequest = new FindAllGroomerBy(serviceProperties.userGroomerUrl(), endpointGroomer.all());
        commandRequest.setRequester(newRequesterForFindAllGroomer());
        return commandRequest;
    }

    private Requester newRequesterForFindAllGroomer() {
        return new Requester() {
            @Override
            public ResponseEntity<?> request() {
                return ResponseEntity.ok(RESPONSE_FOR_FIND_ALL_GROOMER);
            }

            @Override
            public CommandProvider showTypeRequest() {
                return CommandProvider.FIND_ALL;
            }

            @Override
            public RestTemplate getRestTemplate() {
                return builder.build();
            }
        };
    }

}
