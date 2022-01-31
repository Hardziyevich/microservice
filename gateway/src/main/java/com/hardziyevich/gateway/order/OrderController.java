package com.hardziyevich.gateway.order;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/save")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public Long saveOrder(@RequestBody @Valid OrderDto orderDto) {
        System.out.println(orderDto);
        Long aLong = orderService.saveOrder(orderDto);
        System.out.println(aLong);
        return aLong;
    }
}
