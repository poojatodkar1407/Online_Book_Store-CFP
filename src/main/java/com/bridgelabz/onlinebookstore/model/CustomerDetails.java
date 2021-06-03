package com.bridgelabz.onlinebookstore.model;


import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
public class CustomerDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID Costumerid;

    public String pinCode;
    public String locality;
    public String address;
    public String city;
    public String landmark;




    @ManyToOne()
    @JoinColumn(name = "userId")
    public UserDetailsModel userDetails;

}