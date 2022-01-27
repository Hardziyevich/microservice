package com.hardziyevich.order.userorder;

import com.hardziyevich.resource.dto.UserOrderTimeManagementDto;

import java.util.List;

public interface ServiceUserOrder {

    List<UserOrderTimeManagementDto> findDurationOrders(String groomerId);
}
