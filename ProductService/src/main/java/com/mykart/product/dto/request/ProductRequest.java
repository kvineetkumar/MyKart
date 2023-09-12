package com.mykart.product.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
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
}