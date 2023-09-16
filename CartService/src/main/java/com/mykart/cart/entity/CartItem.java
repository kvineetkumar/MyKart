package com.mykart.cart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;

@Entity(name = "cart_items")
public class CartItem {
    @Id
    @UuidGenerator
    private String id;
    private String productId;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;
    private int quantity;
    private BigDecimal price;

    public CartItem() {
    }

    public String getId() {
        return this.id;
    }

    public String getProductId() {
        return this.productId;
    }

    public Cart getCart() {
        return this.cart;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @JsonIgnore
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}