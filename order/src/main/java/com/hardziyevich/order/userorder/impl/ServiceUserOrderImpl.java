package com.hardziyevich.order.userorder.impl;

import com.hardziyevich.order.entity.UserOrder;
import com.hardziyevich.order.userorder.ServiceUserOrder;
import com.hardziyevich.order.userorder.UserOrderRepository;
import com.hardziyevich.resource.dto.RequestToOrderForRegistrationOrderDto;
import com.hardziyevich.resource.dto.ResponseFromUserOrderTimeManagementDto;
import com.hardziyevich.resource.mapper.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ServiceUserOrderImpl implements ServiceUserOrder {

    private final UserOrderRepository userOrderRepository;
    private final Mapper<UserOrder, RequestToOrderForRegistrationOrderDto> mapperUserOrder;
    private final Mapper<ResponseFromUserOrderTimeManagementDto, UserOrder> mapperResponseFromUserOrderTimeManagement;

    public ServiceUserOrderImpl(UserOrderRepository userOrderRepository,
                                Mapper<UserOrder, RequestToOrderForRegistrationOrderDto> mapperUserOrder,
                                Mapper<ResponseFromUserOrderTimeManagementDto, UserOrder> mapperResponseFromUserOrderTimeManagement) {
        this.userOrderRepository = userOrderRepository;
        this.mapperUserOrder = mapperUserOrder;
        this.mapperResponseFromUserOrderTimeManagement = mapperResponseFromUserOrderTimeManagement;
    }

    @Override
    public List<ResponseFromUserOrderTimeManagementDto> findDurationAndTimeOrders(String groomerId, String day) {
        return userOrderRepository.findUserOrderByGroomerIdAndDay(Long.parseLong(groomerId), LocalDate.parse(day)).stream()
                .map(mapperResponseFromUserOrderTimeManagement::mapTo).toList();
    }

    @Override
    public Long saveOrder(RequestToOrderForRegistrationOrderDto request) {
        UserOrder userOrder = mapperUserOrder.mapTo(request);
        UserOrder save = userOrderRepository.save(userOrder);
        return save.getId();
    }

}
