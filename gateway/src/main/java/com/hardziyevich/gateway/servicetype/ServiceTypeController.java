package com.hardziyevich.gateway.servicetype;

import com.hardziyevich.gateway.command.Field;
import com.hardziyevich.gateway.command.Requester;
import com.hardziyevich.resource.dto.ServiceDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/service")
public class ServiceTypeController {

    private final Requester<Long> requester;
    private final String serviceUrl;
    private final String endpoint;

    public ServiceTypeController(@Qualifier("findGroomerIdByNameAndLastName") Requester<Long> requester,
                                 @Value("${service.groomer.service.url}") String serviceUrl,
                                 @Value("${endpoint.groomer.find.service}") String endpoint) {
        this.requester = requester;
        this.serviceUrl = serviceUrl;
        this.endpoint = endpoint;
    }

    @PostMapping("/find")
    public ResponseEntity<String[]> showAllService(@RequestBody @Valid RequestServiceDto requestServiceDto) {
        boolean groomerIsBlank = requestServiceDto.getGroomer().isBlank();
        ResponseEntity<String[]> responseEntity = ResponseEntity.badRequest().build();
        if (groomerIsBlank) {
            responseEntity = responseToService(requestServiceDto.getGroomer(), requestServiceDto.getDay());
        } else {
            Field.GROOMER.setValue(requestServiceDto.getGroomer());
            ResponseEntity<Long> request = requester.request();
            if (request.getStatusCode().is2xxSuccessful() && request.getBody() != null) {
                responseEntity = responseToService(request.getBody().toString(), requestServiceDto.getDay());
            }
        }
        return responseEntity;
    }

    private ResponseEntity<String[]> responseToService(String groomerId, String day) {
        return requester.getRestTemplate().postForEntity(serviceUrl + endpoint, ServiceDto.builder()
                .day(day)
                .groomerId(groomerId)
                .build(), String[].class);
    }

}
