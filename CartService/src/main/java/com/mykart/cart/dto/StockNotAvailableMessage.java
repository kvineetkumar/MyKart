package com.mykart.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StockNotAvailableMessage {
    private String message;
    private List<String> productIds;
}