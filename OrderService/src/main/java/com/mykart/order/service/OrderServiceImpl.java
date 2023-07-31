package com.mykart.order.service;

import com.mykart.order.dto.OrderDto;
import com.mykart.order.entity.Cart;
import com.mykart.order.entity.CardItem;
import com.mykart.order.entity.Product;
import com.mykart.order.repository.OrderItemRepository;
import com.mykart.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
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
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<Cart> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Cart getOrderById(String id) {
        Optional<Cart> orderOptional = orderRepository.findById(id);
        return orderOptional.get();
    }

    @Override
    @Transactional
    public Cart createOrder(OrderDto orderDto) {
        RestTemplate restTemplate = new RestTemplate();
        String url = PRODUCT_URL + orderDto.getProductIds().stream().map(String::valueOf).collect(Collectors.joining(","));
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        List<Product> products = response.getBody();
        var productIds = orderDto.getProductIds();
        Cart cart = new Cart();

        if (products != null && products.size() == orderDto.getProductIds().size()) {
            productIds.forEach(productId -> {
                CardItem cardItem = new CardItem();
                cardItem.setProductId(productId);
                cardItem.setQuantity(1);
                cardItem.setCart(cart);
                cart.getCardItems().add(cardItem);
            });
            return orderRepository.save(cart);
        } else {
            return null;
        }
    }
}