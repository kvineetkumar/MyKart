package com.mykart.cart.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class CartDto {
    @NotEmpty
    private List<String> productIds;

    public @NotEmpty List<String> getProductIds() {
        return this.productIds;
    }

    public void setProductIds(@NotEmpty List<String> productIds) {
        this.productIds = productIds;
    }

    public String toString() {
        return "CartDto(productIds=" + this.getProductIds() + ")";
    }
}
