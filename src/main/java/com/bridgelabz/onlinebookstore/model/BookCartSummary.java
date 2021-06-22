package com.bridgelabz.onlinebookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookCartSummary {


    public UUID BookCartDetailsId;

    public Integer quantity;
    public Double totalPrice;
    public boolean orderStatus;
    public String addedToCartDate;

    public BookDetailsModel bookDetailsModel;


    public CartDetails cartDetails;


    public OderDetailsModel orderDetails;

    public BookCartSummary(BookCartDetails summary) {
        this.BookCartDetailsId= summary.getBookCartDetailsId();
        this.quantity=summary.getQuantity();
        this.totalPrice=summary.getTotalPrice();
        this.orderStatus=isOrderStatus();
        this.addedToCartDate=summary.addedToCartDate;
        this.bookDetailsModel=summary.getBookDetailsModel();
        this.cartDetails=summary.getCartDetails();
        this.orderDetails=summary.getOrderDetails();
    }
}
