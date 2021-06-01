package com.bridgelabz.onlinebookstore.dto;

public class CartDto {

    public Integer id;


    public Integer quantity;
    public Double totalPrice;

    public CartDto() {
    }

    public CartDto(Integer id, Integer quantity,Double totalPrice) {
        this.id = id;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
