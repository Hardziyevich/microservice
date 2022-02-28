package com.hardziyevich.order.userorder.impl;

import com.hardziyevich.order.entity.UserOrder;
import com.hardziyevich.resource.dto.ResponseFromUserOrderTimeManagementDto;
import com.hardziyevich.resource.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class RequestForMapperTimeManagementToUserOrder implements Mapper<ResponseFromUserOrderTimeManagementDto, UserOrder> {

    @Override
    public ResponseFromUserOrderTimeManagementDto mapTo(UserOrder userOrder) {
        return ResponseFromUserOrderTimeManagementDto.builder()
                .time(userOrder.getTime())
                .duration(userOrder.getDuration())
                .build();
    }
}
