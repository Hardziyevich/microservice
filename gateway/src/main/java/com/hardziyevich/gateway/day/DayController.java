package com.hardziyevich.gateway.day;

import com.hardziyevich.gateway.servicetype.RequestServiceDto;
import com.hardziyevich.gateway.servicetype.TypeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/day")
public class DayController {

    private final DayService dayService;

    public DayController(DayService dayService) {
        this.dayService = dayService;
    }

    @PostMapping("/find")
    public List<String> showAllDay(@RequestBody @Valid RequestDayDto requestDayDto) {
        return dayService.showAllDay(requestDayDto);
    }
}
