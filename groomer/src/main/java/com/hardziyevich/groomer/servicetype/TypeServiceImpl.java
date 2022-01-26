package com.hardziyevich.groomer.servicetype;

import com.hardziyevich.groomer.entity.ServiceType;
import com.hardziyevich.resource.dto.ServiceDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class TypeServiceImpl implements TypeService {

    private final ServiceTypeRepository serviceTypeRepository;

    public TypeServiceImpl(ServiceTypeRepository serviceTypeRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
    }

    @Override
    public List<String> showAllService() {
        return serviceTypeRepository.findAll().stream()
                .map(ServiceType::getType)
                .toList();
    }

    @Override
    public List<String> showAllServiceByDay(String day) {
        return serviceTypeRepository.findServiceTypeByGroomerWorkTimesDay(LocalDate.parse(day));
    }

    @Override
    public List<String> showAllServiceByGroomerId(String groomerId) {
        return serviceTypeRepository.findServiceTypeByGroomerId(Long.parseLong(groomerId));
    }

    @Override
    public List<String> showAllServiceByGroomerIdAndDay(ServiceDto dto) {
        return serviceTypeRepository.findServiceTypeByGroomerIdAndDay(
                Long.parseLong(dto.getGroomerId()),
                LocalDate.parse(dto.getDay())
        );
    }

    @Override
    public List<Long> showALlGroomerIdByService(String service) {
        return serviceTypeRepository.findGroomerIdByService(service);
    }
}
