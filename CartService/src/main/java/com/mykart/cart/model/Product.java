package com.mykart.cart.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Product implements Serializable {

    private String id;
    private String name;
    private BigDecimal originalPrice;
    private BigDecimal netPrice;
    private String description;
    private String keywords;
    private Date manufacturedDate;
    private boolean available;

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

    public List<String> getKeywords() {
        return Arrays.stream(keywords.split(", ")).toList();
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = String.join(",", keywords);
    }

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

    public Date getManufacturedDate() {
        return this.manufacturedDate;
    }

    public boolean isAvailable() {
        return this.available;
    }
}
