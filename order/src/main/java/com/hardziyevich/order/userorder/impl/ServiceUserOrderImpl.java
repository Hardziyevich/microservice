package com.hardziyevich.order.userorder.impl;

import com.hardziyevich.order.entity.UserOrder;
import com.hardziyevich.order.userorder.ServiceUserOrder;
import com.hardziyevich.order.userorder.UserOrderRepository;
import com.hardziyevich.resource.dto.RequestToOrderForRegistrationOrderDto;
import com.hardziyevich.resource.dto.ResponseFromUserOrderTimeManagementDto;
import com.hardziyevich.resource.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceUserOrderImpl implements ServiceUserOrder {

    private final UserOrderRepository userOrderRepository;

    public ServiceUserOrderImpl(UserOrderRepository userOrderRepository) {
        this.userOrderRepository = userOrderRepository;
    }

    @Override
    public List<ResponseFromUserOrderTimeManagementDto> findDurationAndTimeOrders(String groomerId, String day) {
        return userOrderRepository.findUserOrderByGroomerIdAndDay(Long.parseLong(groomerId),LocalDate.parse(day)).stream()
                .map(u -> new RequestForMapperTimeManagementToUserOrder().mapTo(u)).toList();
    }

    @Override
    public Long saveOrder(RequestToOrderForRegistrationOrderDto request) {
        UserOrder userOrder = new RequestForSaveOrderToUserOrder().mapTo(request);
        UserOrder save = userOrderRepository.save(userOrder);
        return save.getId();
    }

}
