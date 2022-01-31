package com.hardziyevich.order.userorder;

import com.hardziyevich.resource.dto.RequestToOrderForRegistrationOrderDto;
import com.hardziyevich.resource.dto.RequestToOrderForTimeManagementDto;
import com.hardziyevich.resource.dto.ResponseFromUserOrderTimeManagementDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class UserOrderController {

    private final ServiceUserOrder serviceUserOrder;

    public UserOrderController(ServiceUserOrder serviceUserOrder) {
        this.serviceUserOrder = serviceUserOrder;
    }

    @PostMapping("/duration")
    public List<ResponseFromUserOrderTimeManagementDto> findTimeManagement(@RequestBody @Valid RequestToOrderForTimeManagementDto requestToOrderForTimeManagementDto){
        return serviceUserOrder.findDurationAndTimeOrders(requestToOrderForTimeManagementDto.getGroomerId(), requestToOrderForTimeManagementDto.getDay());
    }

    @PostMapping("/save")
    public Long saveOrder(@RequestBody @Valid RequestToOrderForRegistrationOrderDto request) {
        System.out.println(request);
        return serviceUserOrder.saveOrder(request);
    }

}
