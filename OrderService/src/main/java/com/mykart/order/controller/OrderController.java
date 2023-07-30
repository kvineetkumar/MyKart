package com.mykart.order.controller;

import com.mykart.order.dto.OrderDto;
import com.mykart.order.entity.Order;
import com.mykart.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("{id}")
    ResponseEntity<Order> getOrderById(@PathVariable("id") String id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping
    ResponseEntity<Order> createOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }
}