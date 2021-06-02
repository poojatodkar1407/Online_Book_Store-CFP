package com.bridgelabz.onlinebookstore.model;

import com.bridgelabz.onlinebookstore.dto.CartDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table
public class CartDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID cartId;

    public int quantity;

    @OneToMany(mappedBy = "cartDetails")
    @Where(clause = "order_status=true")
    public List<BookCartDetails> bookCartDetails;

    @OneToOne()
    @JoinColumn(name = "userId")
    public UserDetailsModel userDetailsModel;


}
