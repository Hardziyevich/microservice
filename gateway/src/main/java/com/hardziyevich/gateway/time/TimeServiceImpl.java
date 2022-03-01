package com.hardziyevich.gateway.time;

import com.hardziyevich.gateway.command.Field;
import com.hardziyevich.gateway.command.Requester;
import com.hardziyevich.gateway.config.EndpointGroomerProperties;
import com.hardziyevich.gateway.config.EndpointUserOrderProperties;
import com.hardziyevich.gateway.config.ServiceProperties;
import com.hardziyevich.resource.dto.RequestToGroomerForWorkingTimeDto;
import com.hardziyevich.resource.dto.ResponseFromGroomerForWorkingTimeDto;
import com.hardziyevich.resource.dto.RequestToOrderForTimeManagementDto;
import com.hardziyevich.resource.dto.ResponseFromUserOrderTimeManagementDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

@Service
public class TimeServiceImpl implements TimeService {

    private final Requester requester;
    private final ServiceProperties serviceProperties;
    private final EndpointGroomerProperties endpointGroomerProperties;
    private final EndpointUserOrderProperties endpointUserOrderProperties;

    public TimeServiceImpl(@Qualifier("findGroomerIdByNameAndLastName") Requester requester,
                           ServiceProperties serviceProperties,
                           EndpointGroomerProperties endpointGroomerProperties,
                           EndpointUserOrderProperties endpointUserOrderProperties) {
        this.requester = requester;
        this.serviceProperties = serviceProperties;
        this.endpointGroomerProperties = endpointGroomerProperties;
        this.endpointUserOrderProperties = endpointUserOrderProperties;
    }

    @Override
    public ResponseEntity<List<String>> findFreeWorkingTimeGroomer(TimeDto timeDto) {
        Field.GROOMER.setValue(timeDto.groomer());
        ResponseEntity<List<String>> response = ResponseEntity.badRequest().build();
        ResponseEntity<?> request = requester.request();
        ResponseEntity<ResponseFromGroomerForWorkingTimeDto> durationWorkingTimeForGroomer = findDurationWorkingTimeForGroomer(request, timeDto);
        ResponseEntity<ResponseFromUserOrderTimeManagementDto[]> busyTimeForGroomer = findBusyTimeForGroomer(request, timeDto);
        if (checkResponse(durationWorkingTimeForGroomer, busyTimeForGroomer)) {
            ResponseFromGroomerForWorkingTimeDto workingTImeForGroomer = durationWorkingTimeForGroomer.getBody();
            List<PeriodOrder> busyOrders = busyGroomerTime(busyTimeForGroomer);
            List<PeriodOrder> allTimePeriod = createFreeTimePeriod(
                    workingTImeForGroomer.getStartWork(),
                    workingTImeForGroomer.getEndWork(),
                    workingTImeForGroomer.getDuration());
            List<PeriodOrder> periodOrders = freeTimeForOrder(allTimePeriod, busyOrders);
            List<String> stringStream = periodOrders.stream()
                    .map(p -> String.join("-", p.start().toString(), p.end().toString()))
                    .toList();

            response = ResponseEntity.ok(stringStream);
        }
        return response;
    }

    private ResponseEntity<ResponseFromGroomerForWorkingTimeDto> findDurationWorkingTimeForGroomer(ResponseEntity<?> request, TimeDto timeDto) {
        ResponseEntity<ResponseFromGroomerForWorkingTimeDto> response = ResponseEntity.badRequest().build();
        if (request.getStatusCode().is2xxSuccessful() && request.getBody() != null) {
            RequestToGroomerForWorkingTimeDto requestToGroomer = RequestToGroomerForWorkingTimeDto.builder()
                    .day(timeDto.day())
                    .serviceType(timeDto.serviceType())
                    .groomerId(Long.toString((Long) request.getBody()))
                    .build();
            ResponseEntity<ResponseFromGroomerForWorkingTimeDto> responseFromGroomer = requester.getRestTemplate()
                    .postForEntity(serviceProperties.groomerDayUrl() + endpointGroomerProperties.findWorkingTime(), requestToGroomer, ResponseFromGroomerForWorkingTimeDto.class);
            response = responseFromGroomer.getStatusCode().is2xxSuccessful() &&
                    responseFromGroomer.getBody() != null ? responseFromGroomer : response;
        }
        return response;
    }

    private ResponseEntity<ResponseFromUserOrderTimeManagementDto[]> findBusyTimeForGroomer(ResponseEntity<?> request, TimeDto timeDto) {
        ResponseEntity<ResponseFromUserOrderTimeManagementDto[]> response = ResponseEntity.badRequest().build();
        if (request.getStatusCode().is2xxSuccessful() && request.getBody() != null) {
            RequestToOrderForTimeManagementDto requestUserOrder = RequestToOrderForTimeManagementDto.builder()
                    .day(timeDto.day())
                    .groomerId(Long.toString((Long) request.getBody()))
                    .build();
            ResponseEntity<ResponseFromUserOrderTimeManagementDto[]> responseFromUserOrder = requester.getRestTemplate()
                    .postForEntity(serviceProperties.orderUserUrl() + endpointUserOrderProperties.findBusyTime(), requestUserOrder, ResponseFromUserOrderTimeManagementDto[].class);
            response = responseFromUserOrder.getStatusCode().is2xxSuccessful() &&
                    responseFromUserOrder.getBody() != null ? responseFromUserOrder : response;
        }
        return response;
    }

    private boolean checkResponse(ResponseEntity<ResponseFromGroomerForWorkingTimeDto> responseWorkingTime, ResponseEntity<ResponseFromUserOrderTimeManagementDto[]> responseUserOrder) {
        return responseWorkingTime.getStatusCode().is2xxSuccessful()
                && responseUserOrder.getStatusCode().is2xxSuccessful()
                && responseWorkingTime.getBody() != null
                && responseUserOrder.getBody() != null;
    }

    private List<PeriodOrder> busyGroomerTime(ResponseEntity<ResponseFromUserOrderTimeManagementDto[]> response) {
        ResponseFromUserOrderTimeManagementDto[] body = response.getBody();
        List<PeriodOrder> result = new ArrayList<>();
        if (body != null) {
            result = Arrays.stream(body).map(userOrder -> {
                LocalTime end = userOrder.getTime().plusMinutes(userOrder.getDuration().getMinute());
                return new PeriodOrder(userOrder.getTime(), end);
            }).toList();
        }
        return result;
    }

    private List<PeriodOrder> createFreeTimePeriod(LocalTime start, LocalTime finish, LocalTime serviceTime) {
        List<PeriodOrder> result = new ArrayList<>();
        LocalTime newStartTime = start.plusHours(serviceTime.getHour()).plusMinutes(serviceTime.getMinute());
        int comp = newStartTime.compareTo(finish);
        if (comp == 0 || comp < 0) {
            result.add(new PeriodOrder(start, newStartTime));
            result.addAll(createFreeTimePeriod(newStartTime, finish, serviceTime));
        }
        return result;
    }


    private List<PeriodOrder> freeTimeForOrder(List<PeriodOrder> allTimePeriod, List<PeriodOrder> busyOrders) {
        BiFunction<PeriodOrder, PeriodOrder, Boolean> periodOrderCompare = (o1, o2) -> {
            int endWithStart = o1.end().compareTo(o2.start());
            int startWithEnd = o1.start().compareTo(o2.end());
            if (endWithStart == 0 || endWithStart < 0) {
                return true;
            } else return startWithEnd == 0 || startWithEnd > 0;
        };
        return allTimePeriod.stream()
                .filter(all -> busyOrders.stream()
                        .allMatch(busy -> periodOrderCompare.apply(all, busy)))
                .toList();
    }


}
