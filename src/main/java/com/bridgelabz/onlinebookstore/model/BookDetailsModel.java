package com.bridgelabz.onlinebookstore.model;

import com.bridgelabz.onlinebookstore.dto.BookDto;
import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class BookDetailsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public UUID id;

    public String bookName;
    public String authorName;
    public String description;
    public double bookPrice;
    public double quantity;
    public int rating;
    public int publishingYear;
    public LocalDateTime createdAt = LocalDateTime.now();

//    @OneToMany(mappedBy = "bookDetails")
//    List<BookCartDetails> bookCartDetails;



    public BookDetailsModel(BookDto bookDTO) {
        this.bookName = bookDTO.bookName;
        this.authorName = bookDTO.authorName;
        this.bookPrice = bookDTO.bookPrice;
        this.rating = bookDTO.rating;
        this.quantity = bookDTO.quantity;
        this.description = bookDTO.description;
        this.publishingYear = bookDTO.publishingYear;
    }


}