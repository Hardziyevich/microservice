package com.hardziyevich.groomer.workday;

import com.hardziyevich.groomer.entity.GroomerWorkTime;
import com.hardziyevich.resource.dto.RequestToGroomerForWorkingTimeDto;
import com.hardziyevich.resource.dto.ResponseFromGroomerForWorkingTimeDto;
import com.hardziyevich.resource.dto.WorkingDayDto;
import com.hardziyevich.resource.mapper.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WorkDayServiceImpl implements WorkDayService {

    private final GroomerWorkTimeRepository groomerWorkTimeRepository;
    private final Mapper<ResponseFromGroomerForWorkingTimeDto, GroomerWorkTime> mapper;

    public WorkDayServiceImpl(GroomerWorkTimeRepository groomerWorkTimeRepository, Mapper<ResponseFromGroomerForWorkingTimeDto, GroomerWorkTime> mapper) {
        this.groomerWorkTimeRepository = groomerWorkTimeRepository;
        this.mapper = mapper;
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
    public ResponseEntity<ResponseFromGroomerForWorkingTimeDto> findWorkingTime(RequestToGroomerForWorkingTimeDto dto) {
        Optional<GroomerWorkTime> response = groomerWorkTimeRepository.findGroomerWorkTimeByGroomerIdAndDayAndTypeService(
                Long.parseLong(dto.getGroomerId()),
                LocalDate.parse(dto.getDay()),
                dto.getServiceType()
        );
        return response.map(groomerWorkTime -> ResponseEntity.ok(mapper.mapTo(groomerWorkTime)))
                .orElseGet(() -> ResponseEntity.ok().body(ResponseFromGroomerForWorkingTimeDto.builder().build()));
    }
}
