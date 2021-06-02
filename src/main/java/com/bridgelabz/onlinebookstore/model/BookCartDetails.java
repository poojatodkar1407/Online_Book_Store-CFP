package com.bridgelabz.onlinebookstore.model;


import com.bridgelabz.onlinebookstore.dto.CartDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table
public class BookCartDetails {

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
    @JoinColumn(name = "orderId")
    public OderDetailsModel orderDetails;

    public BookCartDetails(CartDto cartDto){
        this.quantity=cartDto.quantity;
        this.orderStatus= false;
        this.totalPrice=cartDto.totalPrice;
        this.addedToCartDate= LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
    }

    public BookCartDetails() {

    }
}
