package com.mykart.order.service;

import com.mykart.order.dto.OrderDto;
import com.mykart.order.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Order getOrderById(String id);

    Order createOrder(OrderDto orderDto);
}
