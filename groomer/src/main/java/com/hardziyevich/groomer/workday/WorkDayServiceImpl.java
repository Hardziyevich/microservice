package com.hardziyevich.groomer.workday;

import com.hardziyevich.groomer.entity.GroomerWorkTime;
import com.hardziyevich.resource.dto.RequestWorkingTimeDto;
import com.hardziyevich.resource.dto.ResponseWorkingTimeDto;
import com.hardziyevich.resource.dto.WorkingDayDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Long> showALlGroomerIdByWorkingDay(String day) {
        return groomerWorkTimeRepository.findALlGroomerIdByWorkingDay(LocalDate.parse(day));
    }

    @Override
    public List<Long> showAllGroomerIdByWorkingDayAndService(String day, String serviceType) {
        return groomerWorkTimeRepository.findAllGroomerIdByWorkingDayAndService(LocalDate.parse(day), serviceType);
    }

    @Override
    public ResponseEntity<ResponseWorkingTimeDto> findWorkingTime(RequestWorkingTimeDto dto) {
        Optional<GroomerWorkTime> response = groomerWorkTimeRepository.findGroomerWorkTimeByGroomerIdAndDayAndTypeService(
                Long.parseLong(dto.getGroomerId()),
                LocalDate.parse(dto.getDay()),
                dto.getServiceType()
        );
        return response.map(groomerWorkTime -> ResponseEntity.ok(new GroomerWorkTimeToResponseWorkingTimeDto().mapTo(groomerWorkTime)))
                .orElseGet(() -> ResponseEntity.ok().body(ResponseWorkingTimeDto.builder().build()));
    }
}
