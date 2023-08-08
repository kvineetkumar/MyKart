package com.mykart.cart.service;

import com.mykart.cart.dto.CartDto;
import com.mykart.cart.entity.Cart;
import com.mykart.cart.entity.CartItem;
import com.mykart.cart.exception.ResourcesNotFoundException;
import com.mykart.cart.model.Product;
import com.mykart.cart.repository.CartItemRepository;
import com.mykart.cart.repository.CartRepository;
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
public class CartServiceImpl implements CartService {

    private static final String PRODUCT_URL = "http://localhost:8081/api/v1/product/";

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartById(String id) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if (cartOptional.isPresent()) return cartOptional.get();
        else throw new ResourcesNotFoundException("");
    }

    @Override
    @Transactional
    public Cart createCart(CartDto cartDto) {
        RestTemplate restTemplate = new RestTemplate();
        String url = PRODUCT_URL + cartDto.productIds().stream().map(String::valueOf).collect(Collectors.joining(","));
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        List<Product> products = response.getBody();
        var productIds = cartDto.productIds();
        Cart cart = new Cart();

        if (products != null && products.size() == cartDto.productIds().size()) {
            productIds.forEach(productId -> {
                CartItem cartItem = new CartItem();
                cartItem.setProductId(productId);
                cartItem.setQuantity(1);
                cartItem.setCart(cart);
                cart.getCartItems().add(cartItem);
            });
            return cartRepository.save(cart);
        } else {
            return null;
        }
    }

    @Override
    public Product getProductFromCartItem(String cartItemId) {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            var productId = cartItem.getProductId();

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<Product>> response = restTemplate.exchange(
                    PRODUCT_URL + productId,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    }
            );

            List<Product> products = response.getBody();
            if (products != null && !products.isEmpty())
                return products.get(0);
        }
        return null;
    }

    @Override
    public void deleteCart(String id) {
        if (cartRepository.existsById(id))
            cartRepository.deleteById(id);
        else throw new ResourcesNotFoundException("");
    }
}