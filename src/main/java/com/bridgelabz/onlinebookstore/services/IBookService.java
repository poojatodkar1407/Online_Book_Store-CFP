package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.dto.BookDto;
import com.bridgelabz.onlinebookstore.model.BookDetailsModel;

import java.util.List;

public interface IBookService {
    BookDetailsModel addBook(BookDto bookDto);

    List<BookDetailsModel> showAllBooks();

    List<BookDetailsModel> showBookHigherToLower();

    List<BookDetailsModel> showBookLowerToHigher();

    List<BookDetailsModel> showBookNewLaunch();

    int getCountOfBooks();
}
