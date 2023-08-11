package com.mykart.cart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String id;
    private String name;
    private BigDecimal originalPrice;
    private BigDecimal netPrice;
    private String description;
    private String keywords;
    private Date manufacturedDate;

    // Getter and Setter for keywords as a List
    public List<String> getKeywords() {
        return Arrays.stream(keywords.split(", ")).toList();
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = String.join(",", keywords);
    }
}
