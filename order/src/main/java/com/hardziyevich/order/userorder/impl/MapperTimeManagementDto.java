package com.hardziyevich.order.userorder.impl;

import com.hardziyevich.order.entity.UserOrder;
import com.hardziyevich.resource.dto.UserOrderTimeManagementDto;
import com.hardziyevich.resource.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class MapperTimeManagementDto implements Mapper<UserOrderTimeManagementDto, UserOrder> {


    @Override
    public UserOrderTimeManagementDto mapTo(UserOrder userOrder) {
        return UserOrderTimeManagementDto.builder()
                .time(userOrder.getTime())
                .duration(userOrder.getDuration())
                .build();
    }
}
