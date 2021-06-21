package com.bridgelabz.onlinebookstore.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;






@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDetails {

    @Id
    @GeneratedValue(generator = "uuid2",strategy = GenerationType.AUTO)
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    public UUID cartId;



    @OneToMany(mappedBy = "cartDetails")
    @Where(clause = "order_status=true")
    public List<BookCartDetails> bookCartDetails;

    @JsonIgnore
    @OneToOne
    @JoinColumn(referencedColumnName = "userId")
    public UserDetailsModel userDetailsModel;


}
