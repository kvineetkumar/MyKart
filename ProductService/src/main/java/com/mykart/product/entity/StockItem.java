package com.mykart.product.entity;

import jakarta.persistence.*;

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

    public String getId() {
        return this.id;
    }

    public Product getProduct() {
        return this.product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getWarehouseLocation() {
        return this.warehouseLocation;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof StockItem)) return false;
        final StockItem other = (StockItem) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$product = this.getProduct();
        final Object other$product = other.getProduct();
        if (this$product == null ? other$product != null : !this$product.equals(other$product)) return false;
        if (this.getQuantity() != other.getQuantity()) return false;
        final Object this$warehouseLocation = this.getWarehouseLocation();
        final Object other$warehouseLocation = other.getWarehouseLocation();
        if (this$warehouseLocation == null ? other$warehouseLocation != null : !this$warehouseLocation.equals(other$warehouseLocation))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof StockItem;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $product = this.getProduct();
        result = result * PRIME + ($product == null ? 43 : $product.hashCode());
        result = result * PRIME + this.getQuantity();
        final Object $warehouseLocation = this.getWarehouseLocation();
        result = result * PRIME + ($warehouseLocation == null ? 43 : $warehouseLocation.hashCode());
        return result;
    }

    public String toString() {
        return "StockItem(id=" + this.getId() + ", product=" + this.getProduct() + ", quantity=" + this.getQuantity() + ", warehouseLocation=" + this.getWarehouseLocation() + ")";
    }
}
