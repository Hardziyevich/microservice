package com.hardziyevich.gateway.day;

import com.hardziyevich.gateway.command.Field;
import com.hardziyevich.gateway.command.Requester;
import com.hardziyevich.gateway.config.EndpointGroomerProperties;
import com.hardziyevich.gateway.config.ServiceProperties;
import com.hardziyevich.resource.dto.WorkingDayDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/day")
public class DayController {

    private final Requester requester;
    private final ServiceProperties serviceProperties;
    private final EndpointGroomerProperties endpointGroomerProperties;

    public DayController(@Qualifier("findGroomerIdByNameAndLastName") Requester requester,
                         ServiceProperties serviceProperties,
                         EndpointGroomerProperties endpointGroomerProperties) {
        this.requester = requester;
        this.serviceProperties = serviceProperties;
        this.endpointGroomerProperties = endpointGroomerProperties;
    }

    @PostMapping("/find")
    public ResponseEntity<String[]> showAllDay(@RequestBody @Valid RequestDayDto requestDayDto) {
        boolean groomerIsBlank = requestDayDto.groomer().isBlank();
        ResponseEntity<String[]> responseEntity = ResponseEntity.badRequest().build();
        if (groomerIsBlank) {
            responseEntity = responseToService(requestDayDto.groomer(), requestDayDto.serviceType());
        } else {
            Field.GROOMER.setValue(requestDayDto.groomer());
            ResponseEntity<?> request = requester.request();
            if (request.getStatusCode().is2xxSuccessful() && request.getBody() != null) {
                    responseEntity = responseToService(request.getBody().toString(), requestDayDto.serviceType());
            }
        }
        return responseEntity;
    }

    private ResponseEntity<String[]> responseToService(String groomerId, String serviceType) {
        return requester.getRestTemplate().postForEntity(serviceProperties.groomerDayUrl() + endpointGroomerProperties.findDay(), WorkingDayDto.builder()
                .serviceType(serviceType)
                .groomerId(groomerId)
                .build(), String[].class);
    }
}
