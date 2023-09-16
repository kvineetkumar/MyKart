package com.mykart.product.dto.response;

import java.math.BigDecimal;
import java.util.Date;

public class ProductResponse {
    private String id;
    private String name;
    private BigDecimal originalPrice;
    private BigDecimal netPrice;
    private String description;
    private String keywords;
    private Date manufacturedDate;
    private boolean available;

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getOriginalPrice() {
        return this.originalPrice;
    }

    public BigDecimal getNetPrice() {
        return this.netPrice;
    }

    public String getDescription() {
        return this.description;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public Date getManufacturedDate() {
        return this.manufacturedDate;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setNetPrice(BigDecimal netPrice) {
        this.netPrice = netPrice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setManufacturedDate(Date manufacturedDate) {
        this.manufacturedDate = manufacturedDate;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String toString() {
        return "ProductResponse(id=" + this.getId() + ", name=" + this.getName() + ", originalPrice=" + this.getOriginalPrice() + ", netPrice=" + this.getNetPrice() + ", description=" + this.getDescription() + ", keywords=" + this.getKeywords() + ", manufacturedDate=" + this.getManufacturedDate() + ", available=" + this.isAvailable() + ")";
    }
}
