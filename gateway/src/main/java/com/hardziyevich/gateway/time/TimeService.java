package com.hardziyevich.gateway.time;

import com.hardziyevich.resource.dto.ResponseWorkingTimeDto;
import org.springframework.http.ResponseEntity;

public interface TimeService {

    ResponseEntity<ResponseWorkingTimeDto> findWorkingTimeGroomer(TimeDto timeDto);

}
