package com.mykart.cart.dto;

import java.math.BigDecimal;
import java.util.List;

public record CartBillResponse(List<CartItemResponse> cartItems, BigDecimal totalPrice) {

    public record CartItemResponse(String name, BigDecimal price, int GST) {
    }
}
