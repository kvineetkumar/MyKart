package com.mykart.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "cart")
public class Cart {
    @Id
    @UuidGenerator
    @Column(name = "cart_id")
    private String id;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CardItem> cardItems = new ArrayList<>();
}