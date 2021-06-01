package com.bridgelabz.onlinebookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
public class CartDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @OneToMany(mappedBy = "cartDetails")
    @Where(clause = "order_status=true")
    public List<BookCartDetails> book;

    @JsonIgnore
    @OneToOne()
    @JoinColumn(name = "userId")
    public UserDetailsModel userDetails;

    public CartDetails() {
    }
}
