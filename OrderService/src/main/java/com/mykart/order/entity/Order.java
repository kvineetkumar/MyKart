package com.mykart.order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class Order {
    @Id
    @UuidGenerator
    private String id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private List<Product> products;

    public Order(List<Product> products) {
        this.products = products;
    }
}