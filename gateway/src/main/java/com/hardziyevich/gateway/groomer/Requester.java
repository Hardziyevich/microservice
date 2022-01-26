package com.hardziyevich.gateway.groomer;

import org.springframework.http.ResponseEntity;

public interface Requester {

    ResponseEntity<?> request();

}
