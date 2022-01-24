package com.hardziyevich.gateway.servicetype;

import com.hardziyevich.resource.dto.ServiceDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceTypeController {

    private final TypeService typeService;

    public ServiceTypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @PostMapping("/find")
    public List<String> showAllService(@RequestBody @Valid RequestServiceDto requestServiceDto) {
        return typeService.showAllService(requestServiceDto);
    }
}
