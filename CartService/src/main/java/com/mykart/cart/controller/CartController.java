package com.mykart.cart.controller;

import com.mykart.cart.dto.CartBillResponse;
import com.mykart.cart.dto.CartDto;
import com.mykart.cart.entity.Cart;
import com.mykart.cart.model.Product;
import com.mykart.cart.service.CartService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    @ResponseStatus(HttpStatus.FOUND)
    Cart getCartById(@PathVariable("id") @NotNull @NotEmpty String id) {
        return cartService.getCartById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Cart createCart(@RequestBody CartDto cartDto) {
        return cartService.createCart(cartDto);
    }

    @GetMapping("item/{id}/product")
    @ResponseStatus(HttpStatus.FOUND)
    Product getProductFromCartItem(@PathVariable("id") String id) {
        return cartService.getProductFromCartItem(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    String deleteCart(@PathVariable("id") String id) {
        cartService.deleteCart(id);
        return "Cart deleted successfully.";
    }

    @GetMapping({"{id}/bill"})
    @ResponseStatus(value = HttpStatus.OK)
    CartBillResponse getCartBill(@PathVariable("id") String id) {
        return cartService.getCartBill(id);
    }
}