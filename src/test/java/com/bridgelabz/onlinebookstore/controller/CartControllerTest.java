package com.bridgelabz.onlinebookstore.controller;

import com.bridgelabz.onlinebookstore.dto.CartDto;
import com.bridgelabz.onlinebookstore.model.CartDetails;
import com.bridgelabz.onlinebookstore.repository.CartRepository;
import com.bridgelabz.onlinebookstore.services.ICartService;
import com.bridgelabz.onlinebookstore.utils.FileProperties;
import com.bridgelabz.onlinebookstore.dto.ResponseDto;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
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
    public void givenBookDetailsAndCartDetailsToAddInDatabase_WhenAdded_ThenReturnCorrectMessage()
           throws Exception {
          httpHeaders.set("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0ODUwOTY5Ni1iMWM4LTRmNjctYmM4YS1mZmM2ODUwYzYwMDgiLCJzdWIiOiJBbmtpdGEgUGFyaGkiLCJpYXQiOjE2MjI4MzY3NzEsImV4cCI6MTYyMjkzNjc3MH0.aXxBlGACIlxLnGaQfUVCtzcMiQkrN2qCCb6gADGOqZw");
          CartDto cartDto = new CartDto();

          cartDto.setCartId(UUID.fromString("f01e9ad6-95ea-4e9e-b6e1-cf853126dd4e"));
          cartDto.setQuantity(4);
          cartDto.setTotalPrice(3000.00);
          String stringConvertDTO = gson.toJson(cartDto);
          String message = "Book Added To Cart Successfully ";

       MvcResult mvcResult = this.mockMvc.perform(post("/cart/addtocart")
               .contentType(MediaType.APPLICATION_JSON)
               .content(stringConvertDTO).headers(httpHeaders).characterEncoding("utf-8")).andReturn();
       String response = mvcResult.getResponse().getContentAsString();
       Assert.assertEquals("200",new Gson().fromJson(mvcResult.getResponse().getContentAsString(), ResponseDto.class).getStatusCode());



    }
}
