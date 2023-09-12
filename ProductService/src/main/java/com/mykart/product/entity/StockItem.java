package com.mykart.product.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = StockItem.TABLE_NAME)
public class StockItem {

    public static final String TABLE_NAME = "stock_items";

    @Id
    private String id;
    @OneToOne
    @MapsId
    private Product product;
    private int quantity;
    @Column(name = "warehouse_location")
    private String warehouseLocation;
}
