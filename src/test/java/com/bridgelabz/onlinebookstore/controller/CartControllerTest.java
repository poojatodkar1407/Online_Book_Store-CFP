package com.bridgelabz.onlinebookstore.controller;


import com.bridgelabz.onlinebookstore.dto.CartDto;
import com.bridgelabz.onlinebookstore.dto.ResponseDto;
import com.bridgelabz.onlinebookstore.exception.BookStoreException;
import com.bridgelabz.onlinebookstore.repository.CartRepository;
import com.bridgelabz.onlinebookstore.services.ICartService;
import com.bridgelabz.onlinebookstore.utils.FileProperties;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ICartService cartService;

    @MockBean
    public FileProperties fileProperties;

    @MockBean
    CartRepository cartRepository;

    HttpHeaders httpHeaders = new HttpHeaders();
    Gson gson = new Gson();


    @Test
    public void givenBookDetailsAndCartDetailsToAddInDatabase_WhenAdded_ThenReturnCorrectMessage() throws Exception {
        httpHeaders.set("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzZGM4YTQ4MS02NGIyLTQzMDQtODBhMi00M2I0ZmU5YzAwZjIiLCJzdWIiOiJTaGFtYWwgUGF0aWwiLCJpYXQiOjE2MjI4ODQ4NDMsImV4cCI6MTYyMjk4NDg0M30.xxxUkzp36rEJZfaD2rzM5jdsRH0Vs4eZA0OATZlN7yQ");
        CartDto cartDto = new CartDto();
        cartDto.setBookId(UUID.fromString("8a805bb6-e07a-48b9-91da-262a50cce865"));
        cartDto.setQuantity(2);
        cartDto.setTotalPrice(500.00);
        String stringConvertDto = gson.toJson(cartDto);
        String message = "Book Added To Cart Successfully ";

        MvcResult mvcResult = this.mockMvc.perform(post("/cart/addtocart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringConvertDto).headers(httpHeaders)
                .characterEncoding("utf-8"))
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals("200", new Gson().fromJson(mvcResult.getResponse().getContentAsString(), ResponseDto.class).getStatusCode());
    }

    @Test
    void givenBookDetailsAndCartDetailsToAddInDatabase_WhenUserFound_ShouldThrowException() throws Exception {
        try {
            when(cartService.addToCart(any(), any())).thenThrow(new BookStoreException("user not found"));
            this.mockMvc.perform(post("/customer/addDetail_customer")).andReturn();
        } catch (BookStoreException e) {
            Assert.assertSame("user not found", e.getMessage());
        }
    }

    @Test
    public void givenBookDetailsAndCartDetailsToAddInDatabase_WhenCartIsEmpty_ThenReturnStatus() throws Exception {
        httpHeaders.set("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzZGM4YTQ4MS02NGIyLTQzMDQtODBhMi00M2I0ZmU5YzAwZjIiLCJzdWIiOiJTaGFtYWwgUGF0aWwiLCJpYXQiOjE2MjI4ODQ4NDMsImV4cCI6MTYyMjk4NDg0M30.xxxUkzp36rEJZfaD2rzM5jdsRH0Vs4eZA0OATZlN7yQ");
        CartDto cartDto = new CartDto();
        cartDto.setBookId(UUID.fromString("8a805bb6-e07a-48b9-91da-262a50cce86"));
        cartDto.setQuantity(2);
        cartDto.setTotalPrice(500.00);
        String stringConvertDto = gson.toJson(cartDto);
        String message = "Cart Is Empty ";

        MvcResult mvcResult = this.mockMvc.perform(post("/cart/addtocart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringConvertDto).headers(httpHeaders)
                .characterEncoding("utf-8"))
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assert.assertNotEquals(message, new Gson().fromJson(mvcResult.getResponse().getContentAsString(), ResponseDto.class).getStatusCode());
    }

}