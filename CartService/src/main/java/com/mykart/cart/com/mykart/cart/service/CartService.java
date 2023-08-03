package com.mykart.cart.service;

import com.mykart.cart.dto.CartDto;
import com.mykart.cart.entity.Cart;
import com.mykart.cart.model.Product;

import java.util.List;

public interface CartService {

    List<Cart> getAllCarts();

    Cart getCartById(String id);

    Cart createCart(CartDto cartDto);

    Product getProductFromCartItem(String cartItemId);
}
