package com.bridgelabz.onlinebookstore.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
public class BookCartDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer cartDetailsId;
    public Integer quantity;
    public Double totalPrice;
    public boolean orderStatus;
    public String addedToCartDate;

    @ManyToOne()
    @JoinColumn(name = "bookId")
    public BookDetailsModel bookDetailsModel;


    @ManyToOne()
    @JoinColumn(name = "cartId")
    public CartDetails cartDetails;

    @ManyToOne()
    @JoinColumn(name = "orderId")
    public OderDetailsModel orderDetails;



}
