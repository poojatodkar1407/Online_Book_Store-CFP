package com.bridgelabz.onlinebookstore.controller;

import com.bridgelabz.onlinebookstore.dto.BookDto;
import com.bridgelabz.onlinebookstore.dto.ResponseDto;
import com.bridgelabz.onlinebookstore.exception.BookStoreException;
import com.bridgelabz.onlinebookstore.model.BookDetailsModel;
import com.bridgelabz.onlinebookstore.services.CartService;
import com.bridgelabz.onlinebookstore.services.IBookService;
import com.bridgelabz.onlinebookstore.utils.FileProperties;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @MockBean
    private IBookService bookService;


    @MockBean
    public FileProperties fileProperties;

    HttpHeaders httpHeaders = new HttpHeaders();
    BookDto bookDto;
    Gson gson = new Gson();
    List<BookDto> list = new ArrayList<>();


    @Test
    public void givenBookDetailsToAddInDatabase_WhenAdded_ThenReturnCorrectMessage() throws Exception {
        bookDto = new BookDto("Harry", "J K","img.jpg", "Devotional", 5, 700, 2, 1985);
        String toJson = new Gson().toJson(bookDto);
        System.out.println("the to json is " + toJson);
        MvcResult mvcResult = this.mockMvc.perform(post("/book/addBook/")
                .content(toJson)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println(mvcResult.getResponse());
        String message = "BOOK ADDED SUCCESFULLY : ";
        Assert.assertEquals(message, new Gson().fromJson(mvcResult.getResponse().getContentAsString(), ResponseDto.class).getMessage());
    }


    @Test
    public void givenBookDetailsToAddInDatabase_WhenDataInvalid_ShouldThrowException() throws Exception {
        bookDto = new BookDto("Harry", "J K","img.jpg", "Devotional", 5, 700, 2, 1985);
        String toJson = new Gson().toJson(bookDto);
        System.out.println("the to json is "+toJson);
        MvcResult mvcResult = this.mockMvc.perform(post("/book/addBook/")
                .content(toJson)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println(mvcResult.getResponse());
        String message="BOOK ADDED SUCCESFULLY : ";
        Assert.assertEquals(message,new Gson().fromJson(mvcResult.getResponse().getContentAsString(), ResponseDto.class).getMessage());
    }

    @Test
    public void givenRequestToFetchCustomerDetailsFromDatabase_ShouldReturnCorrectData() throws Exception {
        httpHeaders.set("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzZGM4YTQ4MS02NGIyLTQzMDQtODBhMi00M2I0ZmU5YzAwZjIiLCJzdWIiOiJTaGFtYWwgUGF0aWwiLCJpYXQiOjE2MjI4ODQ4NDMsImV4cCI6MTYyMjk4NDg0M30.xxxUkzp36rEJZfaD2rzM5jdsRH0Vs4eZA0OATZlN7yQ");
        bookDto = new BookDto("Harry", "J K","img.jpg", "Devotional", 5, 700, 2, 1985);
        BookDetailsModel bookDetailsModel = new BookDetailsModel(bookDto);
        List<BookDetailsModel> BookList = new ArrayList<>();
        BookList.add(bookDetailsModel);
        String toJson = gson.toJson(bookDetailsModel);
        this.mockMvc.perform(get("/book/getAllBooks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson)
                .headers(httpHeaders)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk());
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


}
