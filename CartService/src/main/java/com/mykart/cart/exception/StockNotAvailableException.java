package com.mykart.cart.exception;

import com.mykart.cart.model.Product;

import java.util.List;

public class StockNotAvailableException extends RuntimeException {

    private final List<Product> products;

    public StockNotAvailableException(List<Product> products) {
        super("Items not available in stock.");
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
