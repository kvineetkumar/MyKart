package com.mykart.cart.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateCartDto extends CartDto {
    @NotEmpty
    private String cartId;
}
