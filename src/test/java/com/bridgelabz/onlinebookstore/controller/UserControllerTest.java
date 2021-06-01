package com.bridgelabz.onlinebookstore.controller;

import com.bridgelabz.onlinebookstore.dto.ResponseDto;
import com.bridgelabz.onlinebookstore.dto.UserDetailsDto;
import com.bridgelabz.onlinebookstore.dto.UserLoginDto;
import com.bridgelabz.onlinebookstore.exception.UserException;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import com.bridgelabz.onlinebookstore.services.UserService;

import com.bridgelabz.onlinebookstore.utils.FileProperties;
import com.bridgelabz.onlinebookstore.utils.Token;
import com.google.gson.Gson;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.http.RequestEntity.post;

@SpringBootTest
@AutoConfigureMockMvc

public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    Token jwttoken;

    @MockBean
    FileProperties fileProperties;

    HttpHeaders httpHeaders = new HttpHeaders();

    Gson gson = new Gson();

   private UserDetailsDto userDetailsDto;
    private UserLoginDto userLoginDto;

    @BeforeEach
    public void setUp(){
        this.userDetailsDto=new UserDetailsDto();
        this.userService=new UserService();
        this.userLoginDto=new UserLoginDto();
    }

    @Test
    public void givenUserDetailsToLoginUser_WhenValidData_ShouldReturnCorrectMessage() throws Exception {
        userLoginDto.emailID="shamalpatil1998@gmail.com";
        userLoginDto.password="Pajusham@98";
        String toJson = new Gson().toJson(userLoginDto);
        String message = "LOGIN SUCCESSFUL";
        MvcResult mvcResult = this.mockMvc.perform(post("/user/login")
                .content(toJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assert.assertTrue(message,mvcResult.getResponse().getContentAsString().contains("LOGIN SUCCESSFUL"));
    }

    @Test
    public void givenUserDetailsToLoginUser_WhenInvalidData_ShouldThrowException() throws Exception {
        userLoginDto.emailID="shamalpatil1998@gmail.com";
    userLoginDto.password="Pajusham@98";
        String toJson = gson.toJson(userLoginDto);
        String message = "LOGIN SUCCESSFUL";
       // when(userService.userLogin(any())).thenThrow(new UserException("Invalid Data!!!!! Please Enter Valid Data", UserException.ExceptionType.INVALID_DATA));
        MvcResult mvcResult = this.mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson)).andReturn();
        Assert.assertFalse(message, mvcResult.getResponse().getContentAsString().contains("Invalid Data!!!!! Please Enter Valid Data"));
    }

}