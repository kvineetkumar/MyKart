package com.mykart.cart.controller;

import com.mykart.cart.dto.CartDto;
import com.mykart.cart.entity.Cart;
import com.mykart.cart.model.Product;
import com.mykart.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cart/")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    ResponseEntity<List<Cart>> getAllCarts() {
        return ResponseEntity.ok(cartService.getAllCarts());
    }

    @GetMapping("{id}")
    ResponseEntity<Cart> getCartById(@PathVariable("id") String id) {
        return ResponseEntity.ok(cartService.getCartById(id));
    }

    @PostMapping
    ResponseEntity<Cart> createCart(@RequestBody CartDto cartDto) {
        return ResponseEntity.ok(cartService.createCart(cartDto));
    }

    @GetMapping("item/{id}/product")
    ResponseEntity<Product> getProductFromCartItem(@PathVariable("id") String id) {
        return ResponseEntity.ok(cartService.getProductFromCartItem(id));
    }
}