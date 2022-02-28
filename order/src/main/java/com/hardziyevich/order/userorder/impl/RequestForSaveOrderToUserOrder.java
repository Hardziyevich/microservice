package com.hardziyevich.order.userorder.impl;

import com.hardziyevich.order.entity.UserOrder;
import com.hardziyevich.resource.dto.RequestToOrderForRegistrationOrderDto;
import com.hardziyevich.resource.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class RequestForSaveOrderToUserOrder implements Mapper<UserOrder, RequestToOrderForRegistrationOrderDto> {

    @Override
    public UserOrder mapTo(RequestToOrderForRegistrationOrderDto request) {
        return UserOrder.builder()
                .userId(request.getUserId())
                .groomerId(request.getGroomerId())
                .petName(request.getPetName())
                .service(request.getService())
                .day(request.getDay())
                .time(request.getTime())
                .duration(request.getDuration())
                .build();
    }
}
