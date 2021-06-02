package com.bridgelabz.onlinebookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class CartDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer cartId;

    @OneToMany(mappedBy = "cartDetails")
    @Where(clause = "order_status=true")
    public List<BookCartDetails> bookCartDetails;

    @OneToOne()
    @JoinColumn(name = "userId")
    public UserDetailsModel userDetailsModel;


}
