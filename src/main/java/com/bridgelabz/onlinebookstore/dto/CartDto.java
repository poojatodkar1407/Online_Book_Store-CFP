package com.bridgelabz.onlinebookstore.dto;

public class CartDto {

    public Integer cartId;


    public Integer quantity;
    public Double totalPrice;

    public CartDto() {
    }

    public CartDto(Integer cartId, Integer quantity,Double totalPrice) {
        this.cartId = cartId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
