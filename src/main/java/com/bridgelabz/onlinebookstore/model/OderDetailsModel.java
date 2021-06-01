package com.bridgelabz.onlinebookstore.model;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
public class OderDetailsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @ManyToOne()
    @JoinColumn(name = "cartId")
    public CartDetails cart;

    @ManyToOne()
    @JoinColumn(name = "userId")
    public UserDetailsModel user;

    @ManyToOne()
    @JoinColumn(name = "customer")
    public CustomerDetails customer;

    @OneToMany(mappedBy = "orderDetails")
    List<BookCartDetails> bookCartDetails;

}
