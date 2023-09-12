package com.mykart.cart.service;

import com.mykart.cart.dto.CartBillResponse;
import com.mykart.cart.dto.CartDto;
import com.mykart.cart.dto.UpdateCartDto;
import com.mykart.cart.entity.Cart;
import com.mykart.cart.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {

    List<Cart> getAllCarts();

    Cart getCartById(String id);

    Cart createCart(CartDto cartDto);

    Product getProductFromCartItem(String cartItemId);

    void deleteCart(String id);

    CartBillResponse getCartBill(String id);

    Cart updateCart(UpdateCartDto updateCartDto);
}
