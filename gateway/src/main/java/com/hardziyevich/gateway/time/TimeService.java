package com.hardziyevich.gateway.time;

import com.hardziyevich.resource.dto.ResponseWorkingTimeDto;
import com.hardziyevich.resource.dto.UserOrderTimeManagementDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TimeService {

    ResponseEntity<List<String>> findFreeWorkingTimeGroomer(TimeDto timeDto);

}
