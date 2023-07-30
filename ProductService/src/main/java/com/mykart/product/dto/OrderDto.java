package com.mykart.product.dto;

import java.util.List;

public record OrderDto(List<String> productIds) {
}
