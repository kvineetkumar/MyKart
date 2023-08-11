package com.mykart.cart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
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
}