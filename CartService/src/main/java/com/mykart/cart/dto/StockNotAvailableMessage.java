package com.mykart.cart.dto;

import java.util.List;

public class StockNotAvailableMessage {
    private String message;
    private List<String> productIds;

    public StockNotAvailableMessage(String message, List<String> productIds) {
        this.message = message;
        this.productIds = productIds;
    }

    public String getMessage() {
        return this.message;
    }

    public List<String> getProductIds() {
        return this.productIds;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }

    public String toString() {
        return "StockNotAvailableMessage(message=" + this.getMessage() + ", productIds=" + this.getProductIds() + ")";
    }
}