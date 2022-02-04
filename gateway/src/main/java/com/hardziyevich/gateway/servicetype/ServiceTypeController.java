package com.hardziyevich.gateway.servicetype;

import com.hardziyevich.gateway.command.Field;
import com.hardziyevich.gateway.command.Requester;
import com.hardziyevich.resource.dto.RequestToGroomerForServiceDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/service")
public class ServiceTypeController {

    private final Requester requester;
    private final String serviceUrl;
    private final String endpoint;

    public ServiceTypeController(@Qualifier("findGroomerIdByNameAndLastName") Requester requester,
                                 @Value("${service.groomer.service.url}") String serviceUrl,
                                 @Value("${endpoint.groomer.find.service}") String endpoint) {
        this.requester = requester;
        this.serviceUrl = serviceUrl;
        this.endpoint = endpoint;
    }

    @PostMapping("/find")
    public ResponseEntity<String[]> showAllService(@RequestBody @Valid ServiceDto serviceDto) {
        boolean groomerIsBlank = serviceDto.getGroomer().isBlank();
        ResponseEntity<String[]> responseEntity = ResponseEntity.badRequest().build();
        if (groomerIsBlank) {
            responseEntity = responseToService(serviceDto.getGroomer(), serviceDto.getDay());
        } else {
            Field.GROOMER.setValue(serviceDto.getGroomer());
            ResponseEntity<?> request = requester.request();
            if (request.getStatusCode().is2xxSuccessful() && request.getBody() != null) {
                responseEntity = responseToService(request.getBody().toString(), serviceDto.getDay());
            }
        }
        return responseEntity;
    }

    private ResponseEntity<String[]> responseToService(String groomerId, String day) {
        return requester.getRestTemplate().postForEntity(serviceUrl + endpoint, RequestToGroomerForServiceDto.builder()
                .day(day)
                .groomerId(groomerId)
                .build(), String[].class);
    }

}
