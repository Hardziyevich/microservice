package com.hardziyevich.gateway.command;

import org.springframework.http.ResponseEntity;

public interface CommandRequest {

    ResponseEntity<?> request();

    CommandProvider showTypeRequest();

}
