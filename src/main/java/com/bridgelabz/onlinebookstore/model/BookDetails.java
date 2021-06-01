package com.bridgelabz.onlinebookstore.model;

import com.bridgelabz.onlinebookstore.dto.BookDto;

import javax.persistence.*;
import java.util.List;

@Entity
public class BookDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer id;

    public String bookName;
    public String authorName;
    public String description;
    public double bookPrice;
    public double quantity;
    public int publishingYear;

    @OneToMany(mappedBy = "bookDetails")
    List<BookCartDetails> bookCartDetails;

    public BookDetails() {
    }

    public BookDetails(BookDto bookDTO) {
        this.bookName = bookDTO.bookName;
        this.authorName = bookDTO.authorName;
        this.bookPrice = bookDTO.bookPrice;
        this.quantity = bookDTO.quantity;
        this.description = bookDTO.description;
        this.publishingYear = bookDTO.publishingYear;
    }
}