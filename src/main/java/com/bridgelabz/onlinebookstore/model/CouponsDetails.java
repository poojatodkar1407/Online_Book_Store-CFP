package com.bridgelabz.onlinebookstore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CouponsDetails implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2",strategy = GenerationType.AUTO)
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    UUID CId;

    @ManyToOne
    @JoinColumn(name = "cuoId")
    public Coupons coupons;

    @ManyToOne
    @JoinColumn(name = "userId")
    public UserDetailsModel user;



    public CouponsDetails(Coupons coupons, UserDetailsModel userDetails) {
        this.coupons = coupons;
        this.user = userDetails;
    }

}
