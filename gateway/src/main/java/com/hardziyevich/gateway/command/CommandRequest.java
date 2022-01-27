package com.hardziyevich.gateway.command;

import org.springframework.http.ResponseEntity;

public interface CommandRequest<T> {

    ResponseEntity<String[]> request();

    CommandProvider showTypeRequest();

    void setRequester(Requester<T> requester);
}
