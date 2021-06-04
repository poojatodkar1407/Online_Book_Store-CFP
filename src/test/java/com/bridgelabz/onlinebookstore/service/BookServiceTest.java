package com.bridgelabz.onlinebookstore.service;

import com.bridgelabz.onlinebookstore.dto.BookDto;
import com.bridgelabz.onlinebookstore.dto.ResponseDto;
import com.bridgelabz.onlinebookstore.exception.BookStoreException;
import com.bridgelabz.onlinebookstore.model.BookDetailsModel;
import com.bridgelabz.onlinebookstore.repository.BookRepository;
import com.bridgelabz.onlinebookstore.services.BookService;
import com.bridgelabz.onlinebookstore.utils.FileProperties;
import com.bridgelabz.onlinebookstore.utils.Token;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class BookServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @MockBean
    FileProperties fileProperties;

    @Mock
    Token jwtToken;


    private BookDto bookDto;


    @Test
    void givenBookDetails_WhenGetResponse_ShouldReturnBookDetails() {
        BookDto bookDTO = new BookDto("Half Girlfriend", "Chethan Bhagath", "Love Story", 3, 200, 2, 2020);
        BookDetailsModel givenBook = new BookDetailsModel(bookDTO);
        try{
            when(bookRepository.findByBookName(any())).thenReturn(java.util.Optional.of(givenBook));
            when(bookRepository.save(any())).thenReturn(givenBook);
            BookDetailsModel addedBooks = bookService.addBook(bookDTO);
            System.out.println(addedBooks);
            String toJson = new Gson().toJson(addedBooks);
            System.out.println("the tojson is "+toJson);
            MvcResult mvcResult = this.mockMvc.perform(post("/addBook/")
                    .content(toJson)
                    .contentType(MediaType.APPLICATION_JSON)).andReturn();
            String message="BOOK ADDED SUCCESSFULLY: ";
            Assert.assertEquals(message,new Gson().fromJson(mvcResult.getResponse().getContentAsString(), ResponseDto.class).getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenBookDetails_WhenBookNameAlreadyPresent_ShouldReturnException() {
        BookDto bookDTO = new BookDto("Half Girlfriend", "Chethan Bhagath", "Love Story", 3, 200, 2, 2020);
        BookDetailsModel givenBook = new BookDetailsModel(bookDTO);

        String message="Book Already present";
        try{
            when(bookRepository.findByBookName(any())).thenReturn(java.util.Optional.of(givenBook));
            when(bookRepository.save(any())).thenReturn(givenBook);
            BookDetailsModel addedBooks = bookService.addBook(bookDTO);
            System.out.println(addedBooks);
            String toJson = new Gson().toJson(addedBooks);
            System.out.println("the tojson is "+toJson);
            MvcResult mvcResult = this.mockMvc.perform(post("/addBook/")
                    .content(toJson)
                    .contentType(MediaType.APPLICATION_JSON)).andReturn();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    void getAllBooks() {
        List<BookDetailsModel> bookList = new ArrayList<>();
        BookDto bookDto = new BookDto("Half Girlfriend", "Chethan Bhagath", "Love Story", 3, 200, 2, 2020);
        BookDetailsModel bookDetails = new BookDetailsModel(bookDto);
        bookList.add(bookDetails);
        when(bookService.showAllBooks()).thenReturn(bookList);
        int size = bookList.size();
        Assert.assertEquals(1, size);
    }

    @Test
    void whenNoBooks_ShouldReturnFalse() {
        List<BookDetailsModel> booksList = new ArrayList<>();
        try {
            when(bookService.showAllBooks()).thenReturn(booksList);
        }
        catch (BookStoreException bookStoreException){
            Assert.assertEquals("no books available",bookStoreException.getMessage());
        }
    }

    @Test
    void givenGetAllBooks_LowToHigh_ReturnBookDetails() {
        List<BookDetailsModel> bookList = new ArrayList<>();
        BookDto bookDto = new BookDto("Half Girlfriend", "Chethan Bhagath", "Love Story", 3, 200, 2, 2020);
        BookDto bookDto1 = new BookDto("The NoteBook", "Nicholas Sparks", "Love Story", 4, 300, 1, 1998);
        BookDetailsModel bookDetails = new BookDetailsModel(bookDto);
        BookDetailsModel bookDetails1 = new BookDetailsModel(bookDto1);
        bookList.add(bookDetails);
        bookList.add(bookDetails1);
        when(bookService.showBookLowerToHigher()).thenReturn(bookList);
        int size = bookList.size();
        Assert.assertEquals(2, size);
    }

    @Test
    void givenGetAllBooks_HighToLow_ReturnBookDetails() {
        List<BookDetailsModel> bookList = new ArrayList<>();
        BookDto bookDto = new BookDto("Half Girlfriend", "Chethan Bhagath", "Love Story", 3, 200, 2, 2020);
        BookDto bookDto1 = new BookDto("The NoteBook", "Nicholas Sparks", "Love Story", 4, 300, 1, 1998);
        BookDetailsModel bookDetails = new BookDetailsModel(bookDto);
        BookDetailsModel bookDetails1 = new BookDetailsModel(bookDto1);
        bookList.add(bookDetails);
        bookList.add(bookDetails1);
        when(bookService.showBookHigherToLower()).thenReturn(bookList);
        int size = bookList.size();
        Assert.assertEquals(2, size);
    }

    @Test
    void givenFetchAllBooks_LowToHigh_ReturnBookDetails() {
        List<BookDetailsModel> bookList = new ArrayList<>();
        BookDto bookDto = new BookDto("Half Girlfriend", "Chethan Bhagath", "Love Story", 3, 200, 2, 2020);
        BookDto bookDto1 = new BookDto("The NoteBook", "Nicholas Sparks", "Love Story", 4, 300, 1, 1998);
        BookDetailsModel bookDetails = new BookDetailsModel(bookDto);
        BookDetailsModel bookDetails1 = new BookDetailsModel(bookDto1);
        bookList.add(bookDetails);
        bookList.add(bookDetails1);
        when(bookService.showBookHigherToLower()).thenReturn(bookList);
        int size = bookList.size();
        Assert.assertEquals(2, size);
    }

    @Test
    void givenFetchAllBooks_HighToLow_ReturnBookDetails() {
        List<BookDetailsModel> bookList = new ArrayList<>();
        BookDto bookDto = new BookDto("Half Girlfriend", "Chethan Bhagath", "Love Story", 3, 200, 2, 2020);
        BookDto bookDto1 = new BookDto("The NoteBook", "Nicholas Sparks", "Love Story", 4, 300, 1, 1998);
        BookDetailsModel bookDetails = new BookDetailsModel(bookDto);
        BookDetailsModel bookDetails1 = new BookDetailsModel(bookDto1);
        bookList.add(bookDetails);
        bookList.add(bookDetails1);
        when(bookService.showBookHigherToLower()).thenReturn(bookList);
        int size = bookList.size();
        Assert.assertEquals(2, size);
    }

    @Test
    void givenFetchAllBooks_NewestArrival_ReturnBookDetails() {
        List<BookDetailsModel> bookList = new ArrayList<>();
        BookDto bookDto = new BookDto("Half Girlfriend", "Chethan Bhagath", "Love Story", 3, 200, 2, 2020);
        BookDto bookDto1 = new BookDto("The NoteBook", "Nicholas Sparks", "Love Story", 4, 300, 1, 1998);
        BookDetailsModel bookDetails = new BookDetailsModel(bookDto);
        BookDetailsModel bookDetails1 = new BookDetailsModel(bookDto1);
        bookList.add(bookDetails);
        bookList.add(bookDetails1);
        when(bookService.showBookNewLaunch()).thenReturn(bookList);
        int size = bookList.size();
        Assert.assertEquals(2, size);
    }
}
