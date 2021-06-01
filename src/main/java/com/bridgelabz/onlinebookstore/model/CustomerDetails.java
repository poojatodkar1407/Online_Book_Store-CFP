package com.bridgelabz.onlinebookstore.model;



import com.bridgelabz.onlinebookstore.utils.AddressType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class CustomerDetails {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    public String pinCode;
    public String locality;
    public String address;
    public String city;
    public String landmark;

    @Enumerated(EnumType.STRING)
    public AddressType addressType;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "userId")
    public UserDetailsModel userDetails;

    @OneToMany(mappedBy = "customer")
    List<OrderDetails> orderDetailsList;

    public CustomerDetails() {
    }

}