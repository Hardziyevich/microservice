package com.hardziyevich.order.userorder;

import com.hardziyevich.resource.dto.UserOrderTimeManagementDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/order")
public class UserOrderController {

    private ServiceUserOrder serviceUserOrder;

    public UserOrderController(ServiceUserOrder serviceUserOrder) {
        this.serviceUserOrder = serviceUserOrder;
    }

    @PostMapping("/duration")
    public List<UserOrderTimeManagementDto> findTimeManagement(@RequestBody @NotBlank String groomerId){
        System.out.println(groomerId);
        return serviceUserOrder.findDurationOrders(groomerId);
    }
}
