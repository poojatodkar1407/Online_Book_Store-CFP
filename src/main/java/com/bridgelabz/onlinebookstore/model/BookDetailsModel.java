package com.bridgelabz.onlinebookstore.model;

import com.bridgelabz.onlinebookstore.dto.BookDto;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class BookDetailsModel implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2",strategy = GenerationType.AUTO)
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    public UUID bookId;

    public String bookName;
    public String authorName;
    public String description;
    public double bookPrice;
    public double quantity;
    public int rating;
    public int publishingYear;
    public LocalDateTime createdAt = LocalDateTime.now();



// @OneToMany( mappedBy = "BookCartDetails", cascade=CascadeType.ALL)
// public List<BookCartDetails> bookCartDetails;



    public BookDetailsModel( String bookName, String authorName, String description, double bookPrice, double quantity, int rating, int publishingYear) {

        this.bookName = bookName;
        this.authorName = authorName;
        this.description = description;
        this.bookPrice = bookPrice;
        this.quantity = quantity;
        this.rating = rating;
        this.publishingYear = publishingYear;

    }

    public BookDetailsModel(BookDto bookDTO) {
        this.bookName = bookDTO.bookName;
        this.authorName = bookDTO.authorName;
        this.bookPrice = bookDTO.bookPrice;
        this.rating = bookDTO.rating;
        this.quantity = bookDTO.quantity;
        this.description = bookDTO.description;
        this.publishingYear = bookDTO.publishingYear;
    }


    public BookDetailsModel(BookDetailsModel bookDetailsModel) {
        this.bookId=bookDetailsModel.getBookId();
        this.bookName=bookDetailsModel.getBookName();
        this.authorName=bookDetailsModel.getAuthorName();
        this.bookPrice=bookDetailsModel.getBookPrice();
        this.rating=bookDetailsModel.getRating();
        this.quantity=bookDetailsModel.getQuantity();
        this.description =bookDetailsModel.getDescription();
        this.publishingYear=bookDetailsModel.getPublishingYear();
        this.createdAt=bookDetailsModel.getCreatedAt();
    }


}