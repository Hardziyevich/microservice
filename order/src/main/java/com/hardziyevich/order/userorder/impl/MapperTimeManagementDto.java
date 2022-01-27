package com.hardziyevich.order.userorder.impl;

import com.hardziyevich.order.entity.UserOrder;
import com.hardziyevich.order.userorder.Mapper;
import com.hardziyevich.resource.dto.UserOrderTimeManagementDto;
import org.springframework.stereotype.Component;

@Component
public class MapperTimeManagementDto implements Mapper<UserOrderTimeManagementDto, UserOrder> {


    @Override
    public UserOrderTimeManagementDto mapTo(UserOrder userOrder) {
        return UserOrderTimeManagementDto.builder()
                .day(userOrder.getDay())
                .time(userOrder.getTime())
                .duration(userOrder.getDuration())
                .build();
    }
}
