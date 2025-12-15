package com.korber.order.controller;

import com.korber.order.dto.OrderRequest;
import com.korber.order.dto.OrderResponse;
import com.korber.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public OrderResponse placeOrder(@RequestBody OrderRequest request) {
        return service.placeOrder(request);
    }
}
