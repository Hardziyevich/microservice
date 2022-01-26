package com.hardziyevich.groomer.workday;

import com.hardziyevich.resource.dto.GroomerDto;
import com.hardziyevich.resource.dto.WorkingDayDto;
import com.hardziyevich.resource.validation.DateValidation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/day")
public class WorkDayController {

    private final WorkDayService workDayService;

    public WorkDayController(WorkDayService workDayService) {
        this.workDayService = workDayService;
    }

    @PostMapping("/search")
    public List<String> showAllDay(@RequestBody @Valid WorkingDayDto request) {
        List<String> result = new ArrayList<>();
        boolean groomerIsBlank = request.getGroomerId().isBlank();
        boolean serviceTypeIsBlank = request.getServiceType().isBlank();

        if (groomerIsBlank && serviceTypeIsBlank) {
            result.addAll(workDayService.showAllWorkingDay());
        } else if (groomerIsBlank) {
            result.addAll(workDayService.showAllWorkingDayByService(request.getServiceType()));
        } else if (serviceTypeIsBlank) {
            result.addAll(workDayService.showAllWorkingDayByGroomerId(request.getGroomerId()));
        } else {
            result.addAll(workDayService.showAllWorkingDayByGroomerIdAndService(request));
        }

        return result;
    }

    @PostMapping("/groomerByDay")
    private List<Long> showAllGroomerId(@RequestBody @DateValidation String day) {
        return workDayService.showALlGroomerIdByWorkingDay(day);
    }

    @PostMapping("/groomerByDayAndService")
    private List<Long> showAllGroomerId(@RequestBody @Valid GroomerDto request) {
        return workDayService.showAllGroomerIdByWorkingDayAndService(request.getDay(),request.getServiceType());
    }

}
