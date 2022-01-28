package com.hardziyevich.gateway.time;

import com.hardziyevich.resource.dto.ResponseWorkingTimeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/time")
public class TimeController {

    private TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @PostMapping("/freeTime")
    public ResponseEntity<ResponseWorkingTimeDto> findFreeTime(@RequestBody @Valid TimeDto timeDto){
        System.out.println(timeDto);
        return timeService.findWorkingTimeGroomer(timeDto);
    }
}
