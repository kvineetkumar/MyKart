package com.mykart.order.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity(name = "products")
@Data
@Setter(AccessLevel.NONE)
public class Product {

    @Id
    @UuidGenerator
    private String id;
    private String name;
    private BigDecimal price;
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
}
