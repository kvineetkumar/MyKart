package com.mykart.product.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ProductRequest {
    @NotEmpty
    private String name;
    @Positive
    private BigDecimal price;
    @NotEmpty
    private String description;
    @NotEmpty
    private List<String> keywords;
    @NotNull
    private Date manufacturedDate;
}