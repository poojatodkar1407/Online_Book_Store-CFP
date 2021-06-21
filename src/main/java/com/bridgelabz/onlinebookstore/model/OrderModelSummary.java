package com.bridgelabz.onlinebookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderModelSummary {
    public UUID id;
    public Integer orderId;
    public Double totalPrice;
    public LocalDate orderPlacedDate;
    public CartDetails cart;
    public UserDetailsModel user;
    public CustomerDetails customer;
    List<BookCartDetails> bookCartDetails;


    public OrderModelSummary(Object summary) {
        this.id=getId();
        this.orderId=getOrderId();
        this.totalPrice=getTotalPrice();
        this.cart=getCart();
        this.user=getUser();
        this.customer=getCustomer();
        this.bookCartDetails=getBookCartDetails();
    }
}
