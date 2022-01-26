package com.hardziyevich.gateway.groomer;

import com.hardziyevich.gateway.command.CommandProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public interface CommandRequestGroomer {

    ResponseEntity<?> request();

    CommandProvider showTypeRequest();

    RestTemplate getRestTemplate();

    String defaultRequestUrl();

    default ResponseEntity<?> defaultRequest() {
        ResponseEntity<?> response = request();
        ResponseEntity<?> result;
        HttpStatus statusCode = response.getStatusCode();
        if (statusCode.is2xxSuccessful() || response.getBody() == null) {
            result = getRestTemplate().postForEntity(defaultRequestUrl(),
                    response.getBody(), String[].class);
        } else {
            result = response;
        }
        return result;
    }

}
