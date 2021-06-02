package com.bridgelabz.onlinebookstore.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class UpDateCartDto extends CartDto {
    public UUID ModeCartId;

    public UpDateCartDto(UUID cartId, Integer quantity, Double totalPrice) {
        super(cartId, quantity, totalPrice);

    }
}
