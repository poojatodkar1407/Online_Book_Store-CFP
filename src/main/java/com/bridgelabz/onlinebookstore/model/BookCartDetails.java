package com.bridgelabz.onlinebookstore.model;


import com.bridgelabz.onlinebookstore.dto.CartDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookCartDetails implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2",strategy = GenerationType.AUTO)
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    public UUID cartDetailsId;
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
    @JoinColumn(name = "oderId")
    public OderDetailsModel orderDetails;



    public BookCartDetails(CartDto cartDto){
        this.quantity=cartDto.quantity;
        this.orderStatus= false;
        this.totalPrice=cartDto.totalPrice;
        this.addedToCartDate= LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
    }
    public BookCartDetails(BookCartDetails bookCartDetails){
        this.cartDetailsId=bookCartDetails.getCartDetailsId();
        this.quantity=bookCartDetails.getQuantity();
        this.orderStatus=bookCartDetails.orderStatus;
        this.totalPrice=bookCartDetails.getTotalPrice();
        this.addedToCartDate=bookCartDetails.getAddedToCartDate();
        this.bookDetailsModel=bookCartDetails.getBookDetailsModel();
        this.cartDetails=bookCartDetails.getCartDetails();
        this.orderDetails=bookCartDetails.getOrderDetails();
    }
}
