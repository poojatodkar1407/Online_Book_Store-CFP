package com.bridgelabz.onlinebookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OderDetailsModel implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2",strategy = GenerationType.AUTO)
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    public UUID id;

    public Integer orderId;
    public Double totalPrice;

    public LocalDate orderPlacedDate;



    @ManyToOne
    @JoinColumn(name = "cartId")
    public CartDetails cart;

    @ManyToOne
    @JoinColumn(name = "userId")
    public UserDetailsModel user;

    @ManyToOne
    @JoinColumn(name = "customer")
    public CustomerDetails customer;

    @OneToMany(mappedBy = "orderDetails")
   List<BookCartDetails> bookCartDetails;

}

