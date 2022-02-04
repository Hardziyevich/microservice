package com.hardziyevich.gateway.command;

import org.springframework.http.ResponseEntity;

public interface CommandRequest {

    ResponseEntity<String[]> request();

    CommandProvider showTypeRequest();

    void setRequester(Requester requester);
}
