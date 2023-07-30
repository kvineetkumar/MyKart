package com.mykart.order.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private List<String> productIds;
}
