package com.bridgelabz.onlinebookstore.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CartDto {

    public UUID cartId;


    public Integer quantity;
    public Double totalPrice;

    public CartDto() {
    }

    public CartDto(UUID cartId, Integer quantity,Double totalPrice) {
        this.cartId = cartId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
