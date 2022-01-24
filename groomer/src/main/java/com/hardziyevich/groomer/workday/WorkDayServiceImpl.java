package com.hardziyevich.groomer.workday;

import com.hardziyevich.resource.dto.WorkingDayDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WorkDayServiceImpl implements WorkDayService {

    private final GroomerWorkTimeRepository groomerWorkTimeRepository;

    public WorkDayServiceImpl(GroomerWorkTimeRepository groomerWorkTimeRepository) {
        this.groomerWorkTimeRepository = groomerWorkTimeRepository;
    }

    @Override
    public List<String> showAllWorkingDay() {
        return groomerWorkTimeRepository.findDistinctDay().stream()
                .map(LocalDate::toString)
                .toList();
    }

    @Override
    public List<String> showAllWorkingDayByService(String serviceType) {
        return groomerWorkTimeRepository.findGroomerWorkTimeByServiceType(serviceType).stream()
                .map(LocalDate::toString)
                .toList();
    }

    @Override
    public List<String> showAllWorkingDayByGroomerId(String groomerId) {
        return groomerWorkTimeRepository.findDistinctDayByGroomerId(Long.parseLong(groomerId)).stream()
                .map(LocalDate::toString)
                .toList();
    }

    @Override
    public List<String> showAllWorkingDayByGroomerIdAndService(WorkingDayDto dto) {
        return groomerWorkTimeRepository.findDistinctDayByGroomerIdAndServiceType(
                        Long.parseLong(dto.getGroomerId()),
                        dto.getServiceType()
                ).stream()
                .map(LocalDate::toString)
                .toList();
    }
}
