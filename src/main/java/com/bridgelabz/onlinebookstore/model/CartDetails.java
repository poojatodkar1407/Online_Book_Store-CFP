package com.bridgelabz.onlinebookstore.model;
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





@ToString
@Entity
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

    public int quantity;
    public double totalPrice;

    @OneToMany(orphanRemoval = true)
    @Where(clause = "order_status=true")
    @JoinColumn(name = "bookdsetails")
    public List<BookCartDetails> bookCartDetails;

    @OneToOne
    @JoinColumn(referencedColumnName = "userId")
    public UserDetailsModel userDetailsModel;


}
