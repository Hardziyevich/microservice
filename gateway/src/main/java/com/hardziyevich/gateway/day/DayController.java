package com.hardziyevich.gateway.day;

import com.hardziyevich.gateway.command.Field;
import com.hardziyevich.gateway.command.Requester;
import com.hardziyevich.resource.dto.ServiceDto;
import com.hardziyevich.resource.dto.WorkingDayDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/day")
public class DayController {

    private final Requester requester;
    private final String serviceUrl;
    private final String endpoint;

    public DayController(@Qualifier("findGroomerIdByNameAndLastName") Requester requester,
                                 @Value("${service.groomer.day.url}") String serviceUrl,
                                 @Value("${endpoint.groomer.find.day}") String endpoint) {
        this.requester = requester;
        this.serviceUrl = serviceUrl;
        this.endpoint = endpoint;
    }

    @PostMapping("/find")
    public List<String> showAllDay(@RequestBody @Valid RequestDayDto requestDayDto) {
        boolean groomerIsBlank = requestDayDto.getGroomer().isBlank();
        ResponseEntity<String[]> listResponseEntity;
        if (groomerIsBlank) {
            listResponseEntity = responseToService(requestDayDto.getGroomer(), requestDayDto.getServiceType());
        } else {
            Field.GROOMER.setValue(requestDayDto.getGroomer());
            ResponseEntity<?> request = requester.request();
            Object body = request.getBody();
            listResponseEntity = responseToService(body.toString(), requestDayDto.getServiceType());
        }
        return Arrays.stream(Objects.requireNonNull(listResponseEntity.getBody())).toList();
    }

    private ResponseEntity<String[]> responseToService(String groomerId,String serviceType) {
        return requester.getRestTemplate().postForEntity(serviceUrl + endpoint, WorkingDayDto.builder()
                .serviceType(serviceType)
                .groomerId(groomerId)
                .build(), String[].class);
    }
}
