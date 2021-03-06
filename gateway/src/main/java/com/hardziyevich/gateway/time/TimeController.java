package com.hardziyevich.gateway.time;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/time")
public class TimeController {

    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @PostMapping("/freeTime")
    public ResponseEntity<List<String>> findFreeTime(@RequestBody @Valid TimeDto timeDto){
        return timeService.findFreeWorkingTimeGroomer(timeDto);
    }
}
