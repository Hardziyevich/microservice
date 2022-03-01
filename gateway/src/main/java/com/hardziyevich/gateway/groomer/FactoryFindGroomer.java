package com.hardziyevich.gateway.groomer;

import com.hardziyevich.gateway.command.CommandRequest;
import com.hardziyevich.gateway.command.Requester;
import com.hardziyevich.gateway.command.impl.FindAllGroomerBy;
import com.hardziyevich.gateway.config.EndpointUserProperties;
import com.hardziyevich.gateway.config.ServiceProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FactoryFindGroomer {

    private final ServiceProperties serviceProperties;
    private final EndpointUserProperties endpointUserProperties;

    public FactoryFindGroomer(ServiceProperties serviceProperties, EndpointUserProperties endpointUserProperties) {
        this.serviceProperties = serviceProperties;
        this.endpointUserProperties = endpointUserProperties;
    }

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

}
