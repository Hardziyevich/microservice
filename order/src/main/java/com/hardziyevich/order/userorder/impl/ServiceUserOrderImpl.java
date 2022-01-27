package com.hardziyevich.order.userorder.impl;

import com.hardziyevich.order.entity.UserOrder;
import com.hardziyevich.order.userorder.Mapper;
import com.hardziyevich.order.userorder.ServiceUserOrder;
import com.hardziyevich.order.userorder.UserOrderRepository;
import com.hardziyevich.resource.dto.UserOrderTimeManagementDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUserOrderImpl implements ServiceUserOrder {

    private final UserOrderRepository userOrderRepository;
    private final Mapper<UserOrderTimeManagementDto, UserOrder> mapper;

    public ServiceUserOrderImpl(UserOrderRepository userOrderRepository, Mapper<UserOrderTimeManagementDto, UserOrder> mapper) {
        this.userOrderRepository = userOrderRepository;
        this.mapper = mapper;
    }

    @Override
    public List<UserOrderTimeManagementDto> findDurationOrders(String groomerId) {
        return userOrderRepository.findUserOrderByGroomerId(Long.parseLong(groomerId)).stream()
                .map(mapper::mapTo).toList();
    }

}
