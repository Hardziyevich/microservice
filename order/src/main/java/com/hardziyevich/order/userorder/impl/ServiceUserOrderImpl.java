package com.hardziyevich.order.userorder.impl;

import com.hardziyevich.order.entity.UserOrder;
import com.hardziyevich.order.userorder.ServiceUserOrder;
import com.hardziyevich.order.userorder.UserOrderRepository;
import com.hardziyevich.resource.dto.UserOrderTimeManagementDto;
import com.hardziyevich.resource.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public List<UserOrderTimeManagementDto> findDurationAndTimeOrders(String groomerId, String day) {
        return userOrderRepository.findUserOrderByGroomerIdAndDay(Long.parseLong(groomerId),LocalDate.parse(day)).stream()
                .map(mapper::mapTo).toList();
    }

}
