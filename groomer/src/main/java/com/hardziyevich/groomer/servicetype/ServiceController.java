package com.hardziyevich.groomer.servicetype;

import com.hardziyevich.resource.dto.RequestToGroomerForServiceDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {

    private final TypeService typeService;

    public ServiceController(TypeService typeService) {
        this.typeService = typeService;
    }

    @PostMapping("/search")
    public List<String> searchByNothingOrDay(@RequestBody @Valid RequestToGroomerForServiceDto request) {
        List<String> result = new ArrayList<>();
        boolean groomerIsBlank = request.getGroomerId().isBlank();
        boolean dayIsBlank = request.getDay().isBlank();
        if(groomerIsBlank && dayIsBlank) {
            result.addAll(typeService.showAllService());
        } else if(groomerIsBlank) {
            result.addAll(typeService.showAllServiceByDay(request.getDay()));
        } else if(dayIsBlank) {
            result.addAll(typeService.showAllServiceByGroomerId(request.getGroomerId()));
        } else {
            result.addAll(typeService.showAllServiceByGroomerIdAndDay(request));
        }
        return result;
    }

    @PostMapping("/groomerByService")
    public List<Long> showAllGroomerId(@RequestBody @NotNull String service) {
        return typeService.showALlGroomerIdByService(service);
    }
}
