package com.hardziyevich.gateway.groomer.impl;

import com.hardziyevich.gateway.command.CommandProvider;
import com.hardziyevich.gateway.command.CommandRequest;
import com.hardziyevich.gateway.command.Requester;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class FindAllGroomerBy implements CommandRequest {

    private final String requestUrlGroomer;
    private final String endpointGroomer;
    private Requester requester;

    public FindAllGroomerBy(String requestUrlGroomer, String endpointGroomer) {
        this.requestUrlGroomer = requestUrlGroomer;
        this.endpointGroomer = endpointGroomer;
    }

    @Override
    public ResponseEntity<?> request() {
        ResponseEntity<?> response = requester.request();
        ResponseEntity<?> result;
        HttpStatus statusCode = response.getStatusCode();
        if (statusCode.is2xxSuccessful() || response.getBody() == null) {
            RestTemplate restTemplate = requester.getRestTemplate();
            result = restTemplate.postForEntity(requestUrlGroomer + endpointGroomer,
                    response.getBody(), String[].class);
        } else {
            result = ResponseEntity.badRequest().body("");
        }
        return result;
    }

    @Override
    public CommandProvider showTypeRequest() {
        return requester.showTypeRequest();
    }

    public void setRequester(Requester requester) {
        this.requester = requester;
    }

}
