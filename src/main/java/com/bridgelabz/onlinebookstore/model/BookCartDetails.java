package com.bridgelabz.onlinebookstore.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class BookCartDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    public Integer quantity;
    public Double totalPrice;

    public boolean orderStatus;
    public String addedToCartDate;

    @ManyToOne()
    @JoinColumn(name = "bookId")
    public BookDetails bookDetails;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "cartId")
    public CartDetails cartDetails;

    @ManyToOne()
    @JoinColumn(name = "orderId")
    public OrderDetails orderDetails;


    public BookCartDetails() {
    }

}
