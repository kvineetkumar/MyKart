package com.mykart.product.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity(name = "products")
public class Product {

    @Id
    @UuidGenerator
    private String id;
    private String name;
    private BigDecimal originalPrice;
    private BigDecimal netPrice;
    private String description;
    @Column(columnDefinition = "VARCHAR(255)")
    private String keywords;
    @Temporal(TemporalType.DATE)
    private Date manufacturedDate;

    // Getter and Setter for keywords as a List
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

    public void setManufacturedDate(Date manufacturedDate) {
        this.manufacturedDate = manufacturedDate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Product)) return false;
        final Product other = (Product) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$originalPrice = this.getOriginalPrice();
        final Object other$originalPrice = other.getOriginalPrice();
        if (this$originalPrice == null ? other$originalPrice != null : !this$originalPrice.equals(other$originalPrice))
            return false;
        final Object this$netPrice = this.getNetPrice();
        final Object other$netPrice = other.getNetPrice();
        if (this$netPrice == null ? other$netPrice != null : !this$netPrice.equals(other$netPrice)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$keywords = this.getKeywords();
        final Object other$keywords = other.getKeywords();
        if (this$keywords == null ? other$keywords != null : !this$keywords.equals(other$keywords)) return false;
        final Object this$manufacturedDate = this.getManufacturedDate();
        final Object other$manufacturedDate = other.getManufacturedDate();
        if (this$manufacturedDate == null ? other$manufacturedDate != null : !this$manufacturedDate.equals(other$manufacturedDate))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Product;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $originalPrice = this.getOriginalPrice();
        result = result * PRIME + ($originalPrice == null ? 43 : $originalPrice.hashCode());
        final Object $netPrice = this.getNetPrice();
        result = result * PRIME + ($netPrice == null ? 43 : $netPrice.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $keywords = this.getKeywords();
        result = result * PRIME + ($keywords == null ? 43 : $keywords.hashCode());
        final Object $manufacturedDate = this.getManufacturedDate();
        result = result * PRIME + ($manufacturedDate == null ? 43 : $manufacturedDate.hashCode());
        return result;
    }

    public String toString() {
        return "Product(id=" + this.getId() + ", name=" + this.getName() + ", originalPrice=" + this.getOriginalPrice() + ", netPrice=" + this.getNetPrice() + ", description=" + this.getDescription() + ", keywords=" + this.getKeywords() + ", manufacturedDate=" + this.getManufacturedDate() + ")";
    }
}