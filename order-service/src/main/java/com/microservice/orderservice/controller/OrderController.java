package com.microservice.orderservice.controller;

import com.microservice.orderservice.dto.OrderRequest;
import com.microservice.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "order create";
    }
    //Con FeignClient
    @PostMapping("/feignClient")
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrderWhitFeignClient(@RequestBody OrderRequest orderRequest){
        orderService.createOrderWhitFeignClient(orderRequest);
        return "Order Create";
    }
}
