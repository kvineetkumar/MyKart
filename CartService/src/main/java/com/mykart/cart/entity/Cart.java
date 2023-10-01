package com.mykart.cart.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "cart")
public class Cart {
    @Id
    @UuidGenerator
    @Column(name = "cart_id")
    private String id;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartItem> cartItems = new ArrayList<>();

    public Cart() {
    }

    public String getId() {
        return this.id;
    }

    public List<CartItem> getCartItems() {
        return this.cartItems;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}