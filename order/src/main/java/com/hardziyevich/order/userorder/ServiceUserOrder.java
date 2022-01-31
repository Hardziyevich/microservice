package com.hardziyevich.order.userorder;

import com.hardziyevich.resource.dto.RequestToOrderForRegistrationOrderDto;
import com.hardziyevich.resource.dto.ResponseFromUserOrderTimeManagementDto;

import java.util.List;

public interface ServiceUserOrder {

    List<ResponseFromUserOrderTimeManagementDto> findDurationAndTimeOrders(String groomerId, String day);

    Long saveOrder(RequestToOrderForRegistrationOrderDto request);
}
