package com.hardziyevich.gateway.command;

import com.hardziyevich.gateway.command.CommandProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public interface Requester {

    ResponseEntity<?> request();

    CommandProvider showTypeRequest();

    RestTemplate getRestTemplate();

}
