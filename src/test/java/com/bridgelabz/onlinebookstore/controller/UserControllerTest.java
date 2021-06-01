package com.bridgelabz.onlinebookstore.controller;

import com.bridgelabz.onlinebookstore.dto.ResponseDto;
import com.bridgelabz.onlinebookstore.services.UserService;
import com.bridgelabz.onlinebookstore.utils.FileProperties;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    UserService userService;

    @MockBean
    FileProperties fileProperties;

//    Gson gson = new Gson();


    @Test
    void givenEmailId_WhenProper_ShouldSendResetPasswordLink() throws Exception{
        String emailId = "mounamc261@gmail.com";
        String message = "Reset Password Link Has Been Sent To Your Email Address";
        MvcResult mvcResult =this.mockMvc.perform(post("/user/forget/password")
                .param("emailID", emailId)).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        ResponseDto responseDto = new Gson().fromJson(response, ResponseDto.class);
        String responseMessage = (String) responseDto.getObject();
        Assert.assertEquals(message, responseMessage);
    }

    @Test
    void givenPassword_WhenProper_ShouldReturnProperMessage() throws Exception{
        String password = "Attitude@007";
        String urlToken = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI3OWRjYWZlMi00Nzk0LTQwMDctYjU4NS00ZjVmNDhkYThiMDQiLCJzdWIiOiJNb3VuYSIsImlhdCI6MTYyMjQ0MzY4NCwiZXhwIjoxNjIyNTQzNjg0fQ.zDCu3Ka9Tslm2wwx8zzXOexVFG-NCfrbOOQohRBjxbg";
        String message = "Password Has Been Reset";
        MvcResult mvcResult = this.mockMvc.perform(post("/user/reset/password/")
                .param("password", password)
                .param("token", urlToken)).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        ResponseDto responseDto = new Gson().fromJson(response, ResponseDto.class);
        String responseMessage = (String) responseDto.getObject();
        Assert.assertEquals(message, responseMessage);
    }

}


