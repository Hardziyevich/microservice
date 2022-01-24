package com.hardziyevich.groomer.workday;

import com.hardziyevich.resource.dto.WorkingDayDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
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

}
