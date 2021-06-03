package com.bridgelabz.onlinebookstore.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Table
@Entity
public class OderDetailsModel {
    @Id
    @GeneratedValue(generator = "uuid2",strategy = GenerationType.AUTO)
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    public UUID id;

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

