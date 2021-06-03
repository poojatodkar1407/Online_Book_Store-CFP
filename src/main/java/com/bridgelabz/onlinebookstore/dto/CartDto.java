package com.bridgelabz.onlinebookstore.dto;

import java.util.UUID;

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
