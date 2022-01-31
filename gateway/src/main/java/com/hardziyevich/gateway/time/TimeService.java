package com.hardziyevich.gateway.time;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TimeService {

    ResponseEntity<List<String>> findFreeWorkingTimeGroomer(TimeDto timeDto);

}
