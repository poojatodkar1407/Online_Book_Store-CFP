package com.bridgelabz.onlinebookstore.model;


import com.bridgelabz.onlinebookstore.utils.AddressType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table
public class CustomerDetails implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID Costumerid;

    public String pinCode;
    public String locality;
    public String address;
    public String city;
    public String landmark;

   public AddressType addressType;


    @ManyToOne()
    @JoinColumn(name = "userId")
    public UserDetailsModel userDetails;

}