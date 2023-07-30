package com.mykart.order.service;

import com.mykart.order.dto.OrderDto;
import com.mykart.order.entity.Order;
import com.mykart.order.entity.Product;
import com.mykart.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final String PRODUCT_URL = "http://localhost:8081/api/v1/product/";

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(String id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        return orderOptional.get();
    }

    @Override
    public Order createOrder(OrderDto orderDto) {
        RestTemplate restTemplate = new RestTemplate();

        String url = PRODUCT_URL + orderDto.getProductIds().stream().map(String::valueOf).collect(Collectors.joining(","));
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        // Get the list of products from the response
        List<Product> products = response.getBody();

        Order order = null;
        if (products != null && products.size() == orderDto.getProductIds().size()) {
            order = new Order(products);
            return orderRepository.save(order);
        } else {
            return order;
        }
    }
}