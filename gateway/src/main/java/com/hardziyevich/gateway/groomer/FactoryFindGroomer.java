package com.hardziyevich.gateway.groomer;

import com.hardziyevich.gateway.command.CommandRequest;
import com.hardziyevich.gateway.command.Requester;
import com.hardziyevich.gateway.command.impl.FindAllGroomerBy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FactoryFindGroomer {

    @Value("${service.user.groomer.url}")
    private String requestUrlGroomer;
    @Value("${endpoint.user.find.by.id}")
    private String endpointGroomer;


    @Bean
    public CommandRequest findAllGroomerByDay(@Qualifier("findDay") Requester requester) {
        CommandRequest commandRequest = new FindAllGroomerBy(requestUrlGroomer,endpointGroomer);
        commandRequest.setRequester(requester);
        return commandRequest;
    }

    @Bean
    public CommandRequest findAllGroomerByService(@Qualifier("findService") Requester requester) {
        CommandRequest commandRequest = new FindAllGroomerBy(requestUrlGroomer,endpointGroomer);
        commandRequest.setRequester(requester);
        return commandRequest;
    }

    @Bean
    public CommandRequest findAllGroomerByDayAndService(@Qualifier("findDayAndService") Requester requester) {
        CommandRequest commandRequest = new FindAllGroomerBy(requestUrlGroomer,endpointGroomer);
        commandRequest.setRequester(requester);
        return commandRequest;
    }

}
