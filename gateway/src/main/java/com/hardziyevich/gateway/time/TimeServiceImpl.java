package com.hardziyevich.gateway.time;

import com.hardziyevich.gateway.command.Field;
import com.hardziyevich.gateway.command.Requester;
import com.hardziyevich.resource.dto.RequestWorkingTimeDto;
import com.hardziyevich.resource.dto.ResponseWorkingTimeDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TimeServiceImpl implements TimeService {

    private Requester<Long> requester;
    private String requestUrlGroomer;
    private String endpointGroomer;

    public TimeServiceImpl(@Qualifier("findGroomerIdByNameAndLastName") Requester<Long> requester,
                           @Value("${service.groomer.day.url}") String requestUrlGroomer,
                           @Value("${endpoint.groomer.find.working.time}") String endpointGroomer) {
        this.requester = requester;
        this.requestUrlGroomer = requestUrlGroomer;
        this.endpointGroomer = endpointGroomer;
    }

    @Override
    public ResponseEntity<ResponseWorkingTimeDto> findWorkingTimeGroomer(TimeDto timeDto) {
        Field.GROOMER.setValue(timeDto.getGroomer());
        ResponseEntity<ResponseWorkingTimeDto> response = ResponseEntity.badRequest().build();
        ResponseEntity<Long> request = requester.request();
        if (request.getStatusCode().is2xxSuccessful() && request.getBody() != null) {
            RequestWorkingTimeDto requestToGroomer = RequestWorkingTimeDto.builder()
                    .day(timeDto.getDay())
                    .serviceType(timeDto.getServiceType())
                    .groomerId(Long.toString(request.getBody()))
                    .build();
            ResponseEntity<ResponseWorkingTimeDto> responseFromGroomer = requester.getRestTemplate()
                    .postForEntity(requestUrlGroomer + endpointGroomer, requestToGroomer, ResponseWorkingTimeDto.class);
            response = responseFromGroomer.getStatusCode().is2xxSuccessful() &&
                    responseFromGroomer.getBody() != null ? responseFromGroomer : response;
        }
        return response;
    }
}
