package com.mykart.cart.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    @NotEmpty
    private List<String> productIds;
}
