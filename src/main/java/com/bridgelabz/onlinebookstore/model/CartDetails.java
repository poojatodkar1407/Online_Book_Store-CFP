package com.bridgelabz.onlinebookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
//@Data
@Entity
//@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDetails implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2",strategy = GenerationType.AUTO)
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    public UUID cartId;

    @OneToMany()
    @Where(clause = "order_status=true")
    @CollectionTable(joinColumns = @JoinColumn(name = "cartDetailsId"))
    public List<BookCartDetails> bookCartDetails;

    @OneToOne()
    @JoinColumn(referencedColumnName = "userId")
    public UserDetailsModel userDetailsModel;


}
