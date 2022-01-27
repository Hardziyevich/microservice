package com.hardziyevich.gateway.command.impl;

import com.hardziyevich.gateway.command.CommandProvider;
import com.hardziyevich.gateway.command.CommandRequest;
import com.hardziyevich.gateway.command.Requester;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class FindAllGroomerBy implements CommandRequest<Long[]>  {

    private final String requestUrlGroomer;
    private final String endpointGroomer;
    private Requester<Long[]>  requester;

    public FindAllGroomerBy(String requestUrlGroomer, String endpointGroomer) {
        this.requestUrlGroomer = requestUrlGroomer;
        this.endpointGroomer = endpointGroomer;
    }

    @Override
    public ResponseEntity<String[]> request() {
        ResponseEntity<Long[]> response = requester.request();
        ResponseEntity<String[]> result = ResponseEntity.badRequest().build();
        HttpStatus statusCode = response.getStatusCode();
        if (statusCode.is2xxSuccessful() && response.getBody() != null) {
            RestTemplate restTemplate = requester.getRestTemplate();
            result = restTemplate.postForEntity(requestUrlGroomer + endpointGroomer,
                    response.getBody(), String[].class);
        }
        return result;
    }

    @Override
    public CommandProvider showTypeRequest() {
        return requester.showTypeRequest();
    }

    public void setRequester(Requester<Long[]>  requester) {
        this.requester = requester;
    }

}
