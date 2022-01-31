package com.hardziyevich.gateway.time;

import com.hardziyevich.gateway.command.Field;
import com.hardziyevich.gateway.command.Requester;
import com.hardziyevich.resource.dto.RequestToGroomerForWorkingTimeDto;
import com.hardziyevich.resource.dto.ResponseFromGroomerForWorkingTimeDto;
import com.hardziyevich.resource.dto.RequestToOrderForTimeManagementDto;
import com.hardziyevich.resource.dto.ResponseFromUserOrderTimeManagementDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

@Service
public class TimeServiceImpl implements TimeService {

    private Requester<Long> requester;
    private String requestUrlGroomer;
    private String endpointGroomer;
    private String requestUrlUserOrder;
    private String endpointUserOrder;

    public TimeServiceImpl(@Qualifier("findGroomerIdByNameAndLastName") Requester<Long> requester,
                           @Value("${service.groomer.day.url}") String requestUrlGroomer,
                           @Value("${endpoint.groomer.find.working.time}") String endpointGroomer,
                           @Value("${service.userorder.order.url}") String requestUrlUserOrder,
                           @Value("${endpoint.userorder.find.busytime}") String endpointUserOrder) {
        this.requester = requester;
        this.requestUrlGroomer = requestUrlGroomer;
        this.endpointGroomer = endpointGroomer;
        this.requestUrlUserOrder = requestUrlUserOrder;
        this.endpointUserOrder = endpointUserOrder;
    }

    @Override
    public ResponseEntity<List<String>> findFreeWorkingTimeGroomer(TimeDto timeDto) {
        Field.GROOMER.setValue(timeDto.getGroomer());
        ResponseEntity<List<String>> response = ResponseEntity.badRequest().build();
        ResponseEntity<Long> request = requester.request();
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
                    .map(p -> String.join("-", p.getStart().toString(), p.getEnd().toString()))
                    .toList();

            response = ResponseEntity.ok(stringStream);
        }
        return response;
    }

    private ResponseEntity<ResponseFromGroomerForWorkingTimeDto> findDurationWorkingTimeForGroomer(ResponseEntity<Long> request, TimeDto timeDto) {
        ResponseEntity<ResponseFromGroomerForWorkingTimeDto> response = ResponseEntity.badRequest().build();
        if (request.getStatusCode().is2xxSuccessful() && request.getBody() != null) {
            RequestToGroomerForWorkingTimeDto requestToGroomer = RequestToGroomerForWorkingTimeDto.builder()
                    .day(timeDto.getDay())
                    .serviceType(timeDto.getServiceType())
                    .groomerId(Long.toString(request.getBody()))
                    .build();
            ResponseEntity<ResponseFromGroomerForWorkingTimeDto> responseFromGroomer = requester.getRestTemplate()
                    .postForEntity(requestUrlGroomer + endpointGroomer, requestToGroomer, ResponseFromGroomerForWorkingTimeDto.class);
            response = responseFromGroomer.getStatusCode().is2xxSuccessful() &&
                    responseFromGroomer.getBody() != null ? responseFromGroomer : response;
        }
        return response;
    }

    private ResponseEntity<ResponseFromUserOrderTimeManagementDto[]> findBusyTimeForGroomer(ResponseEntity<Long> request, TimeDto timeDto) {
        ResponseEntity<ResponseFromUserOrderTimeManagementDto[]> response = ResponseEntity.badRequest().build();
        if (request.getStatusCode().is2xxSuccessful() && request.getBody() != null) {
            RequestToOrderForTimeManagementDto requestUserOrder = RequestToOrderForTimeManagementDto.builder()
                    .day(timeDto.getDay())
                    .groomerId(Long.toString(request.getBody()))
                    .build();
            ResponseEntity<ResponseFromUserOrderTimeManagementDto[]> responseFromUserOrder = requester.getRestTemplate()
                    .postForEntity(requestUrlUserOrder + endpointUserOrder, requestUserOrder, ResponseFromUserOrderTimeManagementDto[].class);
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
                return PeriodOrder.of(userOrder.getTime(), end);
            }).toList();
        }
        return result;
    }

    private List<PeriodOrder> createFreeTimePeriod(LocalTime start, LocalTime finish, LocalTime serviceTime) {
        List<PeriodOrder> result = new ArrayList<>();
        LocalTime newStartTime = start.plusHours(serviceTime.getHour()).plusMinutes(serviceTime.getMinute());
        int comp = newStartTime.compareTo(finish);
        if (comp == 0 || comp < 0) {
            result.add(PeriodOrder.of(start, newStartTime));
            result.addAll(createFreeTimePeriod(newStartTime, finish, serviceTime));
        }
        return result;
    }


    private List<PeriodOrder> freeTimeForOrder(List<PeriodOrder> allTimePeriod, List<PeriodOrder> busyOrders) {
        BiFunction<PeriodOrder, PeriodOrder, Boolean> periodOrderCompare = (o1, o2) -> {
            int endWithStart = o1.getEnd().compareTo(o2.getStart());
            int startWithEnd = o1.getStart().compareTo(o2.getEnd());
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