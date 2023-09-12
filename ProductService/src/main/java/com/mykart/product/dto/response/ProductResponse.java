package com.mykart.product.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductResponse {
    private String id;
    private String name;
    private BigDecimal originalPrice;
    private BigDecimal netPrice;
    private String description;
    private String keywords;
    private Date manufacturedDate;
    private boolean available;
}
