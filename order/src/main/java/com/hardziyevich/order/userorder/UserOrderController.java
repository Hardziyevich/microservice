package com.hardziyevich.order.userorder;

import com.hardziyevich.resource.dto.UserOrderDto;
import com.hardziyevich.resource.dto.UserOrderTimeManagementDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/order")
public class UserOrderController {

    private ServiceUserOrder serviceUserOrder;

    public UserOrderController(ServiceUserOrder serviceUserOrder) {
        this.serviceUserOrder = serviceUserOrder;
    }

    @PostMapping("/duration")
    public List<UserOrderTimeManagementDto> findTimeManagement(@RequestBody @Valid UserOrderDto userOrderDto){
        return serviceUserOrder.findDurationAndTimeOrders(userOrderDto.getGroomerId(), userOrderDto.getDay());
    }
}
