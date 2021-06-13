package com.bridgelabz.onlinebookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "wishlistitems")
public class WishListItems implements Serializable {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Integer wishListItemsId;

    @CreationTimestamp
    private Timestamp addedToCartDate;

    @ManyToOne()
    @JoinColumn(name = "bookId")
    private BookDetailsModel bookID;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "wishListId")
    private WishList wishList;

    public WishListItems(BookDetailsModel bookID, WishList wishList) {
        this.bookID = bookID;
        this.wishList = wishList;
    }
}
