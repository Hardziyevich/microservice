package com.hardziyevich.gateway.order;

import com.hardziyevich.resource.dto.RequestToOrderForRegistrationOrderDto;
import com.hardziyevich.resource.mapper.Mapper;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class OrderDtoToRequestOrderForRegistrationDto implements Mapper<RequestToOrderForRegistrationOrderDto,OrderDto> {

    @Override
    public RequestToOrderForRegistrationOrderDto mapTo(OrderDto orderDto) {
        TimeManagement timeManagement = TimeManagement.parse(orderDto);
        return RequestToOrderForRegistrationOrderDto.builder()
                .petName(orderDto.petName())
                .service(orderDto.serviceType())
                .day(LocalDate.parse(orderDto.day()))
                .time(timeManagement.getTime())
                .duration(timeManagement.duration)
                .build();
    }

    @Data
    @Builder
    private static class TimeManagement {
        private LocalTime time;
        private LocalTime duration;

        static TimeManagement parse(OrderDto orderDto) {
            String[] parseTime = orderDto.timeType().split("-");
            LocalTime start = LocalTime.parse(parseTime[0]);
            LocalTime end = LocalTime.parse(parseTime[1]);
            LocalTime duration = end.minusHours(start.getHour()).minusMinutes(start.getMinute());
            return TimeManagement.builder()
                    .time(start)
                    .duration(duration)
                    .build();
        }
    }
}
