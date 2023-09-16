package com.mykart.cart.dto;

import jakarta.validation.constraints.NotEmpty;

public class UpdateCartDto extends CartDto {
    @NotEmpty
    private String cartId;

    public @NotEmpty String getCartId() {
        return this.cartId;
    }

    public void setCartId(@NotEmpty String cartId) {
        this.cartId = cartId;
    }
}
