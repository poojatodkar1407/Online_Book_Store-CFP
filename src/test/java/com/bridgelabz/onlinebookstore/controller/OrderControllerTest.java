package com.bridgelabz.onlinebookstore.controller;

import com.bridgelabz.onlinebookstore.dto.ResponseDto;
import com.bridgelabz.onlinebookstore.services.IOrderService;
import com.bridgelabz.onlinebookstore.utils.FileProperties;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IOrderService orderService;

    @MockBean
    FileProperties fileProperties;


    Gson gson = new Gson();
    HttpHeaders httpHeaders = new HttpHeaders();

    @Test
    void givenOrderDetails_WhenOrderPlaced_ShouldReturnMessage() throws Exception {
        httpHeaders.set("token", "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2YjFhY2ZlMy0xNDkxLTQ5N2UtOTUyNi1hZDVlYTA4NjU1ZmYiLCJzdWIiOiJZZW5uZXIiLCJpYXQiOjE2MjI4Mjc0MTMsImV4cCI6MTYyMjkyNzQxM30.TleAMgju7kHBU2jdKA6jm_bHkO3eGt-vq3rTgZwudns");
        String message = "Order Placed succesFully : ";
        String totalPrice = "500.00";
        MvcResult mvcResult = this.mockMvc.perform(post("/customer/addorder")
                .param("totalPrice", totalPrice)
                .headers(httpHeaders).characterEncoding("utf-8"))
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        ResponseDto responseDto = gson.fromJson(response, ResponseDto.class);
        String responseMessage = responseDto.message;
        Assert.assertEquals(message, responseMessage);
    }
}