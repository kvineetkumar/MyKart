package com.mykart.product.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProductRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private List<String> keywords;
    @Positive
    private BigDecimal originalPrice;
    @Positive
    private BigDecimal netPrice;
    @NotNull
    private Date manufacturedDate;

    public @NotEmpty String getName() {
        return this.name;
    }

    public @NotEmpty String getDescription() {
        return this.description;
    }

    public @NotEmpty List<String> getKeywords() {
        return this.keywords;
    }

    public @Positive BigDecimal getOriginalPrice() {
        return this.originalPrice;
    }

    public @Positive BigDecimal getNetPrice() {
        return this.netPrice;
    }

    public @NotNull Date getManufacturedDate() {
        return this.manufacturedDate;
    }

    public void setName(@NotEmpty String name) {
        this.name = name;
    }

    public void setDescription(@NotEmpty String description) {
        this.description = description;
    }

    public void setKeywords(@NotEmpty List<String> keywords) {
        this.keywords = keywords;
    }

    public void setOriginalPrice(@Positive BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setNetPrice(@Positive BigDecimal netPrice) {
        this.netPrice = netPrice;
    }

    public void setManufacturedDate(@NotNull Date manufacturedDate) {
        this.manufacturedDate = manufacturedDate;
    }

    public String toString() {
        return "ProductRequest(name=" + this.getName() + ", description=" + this.getDescription() + ", keywords=" + this.getKeywords() + ", originalPrice=" + this.getOriginalPrice() + ", netPrice=" + this.getNetPrice() + ", manufacturedDate=" + this.getManufacturedDate() + ")";
    }
}