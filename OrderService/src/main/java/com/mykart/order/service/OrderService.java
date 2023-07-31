package com.mykart.order.service;

import com.mykart.order.dto.OrderDto;
import com.mykart.order.entity.Cart;

import java.util.List;

public interface OrderService {

    List<Cart> getAllOrders();

    Cart getOrderById(String id);

    Cart createOrder(OrderDto orderDto);
}
