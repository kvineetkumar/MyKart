package com.mykart.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "order_items")
public class OrderItem {
    @Id
    private String id;
    private String productId;
    private String orderId;
    private int quantity;
}
