package com.bridgelabz.onlinebookstore.controller;

import com.bridgelabz.onlinebookstore.dto.ResponseDto;
import com.bridgelabz.onlinebookstore.dto.UserDetailsDto;
import com.bridgelabz.onlinebookstore.dto.UserLoginDto;
import com.bridgelabz.onlinebookstore.exception.UserException;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import com.bridgelabz.onlinebookstore.repository.UserDetailsRepository;
import com.bridgelabz.onlinebookstore.services.IUserService;
import com.bridgelabz.onlinebookstore.services.UserService;
import com.bridgelabz.onlinebookstore.utils.FileProperties;
import com.bridgelabz.onlinebookstore.utils.Token;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.validation.BindingResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;


     private UserService userService;

    @MockBean
    Token token;

    @MockBean
    UserDetailsRepository userDetailsRepository;

    @MockBean
    FileProperties fileProperties;

    HttpHeaders httpHeaders=new HttpHeaders();
//    Gson gson = new Gson();

    private  UserDetailsDto userDetailsDto;
    private UserLoginDto userLoginDto;

    @BeforeEach
    public void setUp(){
        this.userDetailsDto=new UserDetailsDto();
        this.userService=new UserService();
        this.userLoginDto=new UserLoginDto();
    }

    @Test
    void givenUserRegistration_WhenValidationAreTrue_ShouldReturnMessage() throws Exception {
        userDetailsDto.fullName="Ankita Parhi";
        userDetailsDto.emailID="parhiankita@gmail.com";
        userDetailsDto.password="Ankita@9713";
        userDetailsDto.phoneNumber="917077757574";
       // UserDetailsModel userDetailsModel = userService.addUser(userDetailsDto);

        String toJson = new Gson().toJson(userDetailsDto);

        System.out.println("the tojson is "+toJson);
        MvcResult mvcResult = this.mockMvc.perform(post("/user/register")
                                .content(toJson)
                                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println(mvcResult.getResponse());

         String message="USER ADDED SUCCESSFULLY: ";



           // Assert.assertEquals(200,mvcResult.getResponse().getStatus());
             Assert.assertEquals(message,new Gson().fromJson(mvcResult.getResponse().getContentAsString(),ResponseDto.class).getMessage());
//            String response = mvcResult.getResponse().getContentAsString();
//            ResponseDto responseDto = gson.fromJson(response, ResponseDto.class);
//            String responseMessage = responseDto.getMessage();
//            Assert.assertEquals(message,responseMessage);


    }

    @Test
    void givenUserRegistration_WhenValidationIsNotDoneCorrectWithEmail_ShouldReturnMessage() throws Exception {

        userDetailsDto.fullName="Ankita Parhi";
        userDetailsDto.emailID="parhiankita@.com";
        userDetailsDto.password="Ankita@9713";
        userDetailsDto.phoneNumber="917077757574";
        //UserDetailsModel userDetailsModel = userService.addUser(userDetailsDto);

        String toJson = new Gson().toJson(userDetailsDto);

        System.out.println("the tojson is "+toJson);
        MvcResult mvcResult = this.mockMvc.perform(post("/user/register")
                .content(toJson)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println(mvcResult.getResponse());

        String message="USER ADDED SUCCESSFULLY: ";
        Assert.assertNotEquals(message,new Gson().fromJson(mvcResult.getResponse().getContentAsString(),ResponseDto.class).getMessage());


    }

    @Test
    void givenUserRegistration_WhenValidationIsNotDoneCorrectWithFullName_ShouldReturnMessage() throws Exception {

        userDetailsDto.fullName="ankita parhi";
        userDetailsDto.emailID="parhiankita@gmail.com";
        userDetailsDto.password="Ankita@9713";
        userDetailsDto.phoneNumber="917077757574";
        //UserDetailsModel userDetailsModel = userService.addUser(userDetailsDto);

        String toJson = new Gson().toJson(userDetailsDto);

        System.out.println("the tojson is "+toJson);
        MvcResult mvcResult = this.mockMvc.perform(post("/user/register")
                                          .content(toJson)
                                          .contentType(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println(mvcResult.getResponse());

        String message="USER ADDED SUCCESSFULLY: ";
        Assert.assertNotEquals(message,new Gson().fromJson(mvcResult.getResponse().getContentAsString(),ResponseDto.class).getMessage());


    }


    @Test
    void givenUserRegistration_WhenValidationIsNotDoneCorrectWithPassword_ShouldReturnMessage() throws Exception {

        userDetailsDto.fullName="ankita parhi";
        userDetailsDto.emailID="parhiankita@gmail.com";
        userDetailsDto.password="ankita@9713";
        userDetailsDto.phoneNumber="917077757574";
        //UserDetailsModel userDetailsModel = userService.addUser(userDetailsDto);

        String toJson = new Gson().toJson(userDetailsDto);

        System.out.println("the tojson is "+toJson);
        MvcResult mvcResult = this.mockMvc.perform(post("/user/register")
                .content(toJson)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println(mvcResult.getResponse());

        String message="USER ADDED SUCCESSFULLY: ";
        Assert.assertNotEquals(message,new Gson().fromJson(mvcResult.getResponse().getContentAsString(),ResponseDto.class).getMessage());


    }

    @Test
    void givenUserRegistration_WhenValidationIsNotDoneCorrectWithPhoneNumber_ShouldReturnMessage() throws Exception {

        userDetailsDto.fullName="ankita parhi";
        userDetailsDto.emailID="parhiankita@gmail.com";
        userDetailsDto.password="ankita@9713";
        userDetailsDto.phoneNumber="9170777574";
        //UserDetailsModel userDetailsModel = userService.addUser(userDetailsDto);

        String toJson = new Gson().toJson(userDetailsDto);

        System.out.println("the tojson is "+toJson);
        MvcResult mvcResult = this.mockMvc.perform(post("/user/register")
                                          .content(toJson)
                                             .contentType(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println(mvcResult.getResponse());

        String message="USER ADDED SUCCESSFULLY: ";
        Assert.assertNotEquals(message,new Gson().fromJson(mvcResult.getResponse().getContentAsString(),ResponseDto.class).getMessage());


    }






    @Test
    public void givenUserDetailsToLoginUser_WhenValidData_ShouldReturnCorrectMessage() throws Exception {
         userLoginDto.emailID="parhisasmita72@gmail.com";
         userLoginDto.password="Ankita@9713";
         String toJson = new Gson().toJson(userLoginDto);
        String message = "LOGIN SUCCESSFUL";
        //when(userService.userLogin(any())).thenReturn(message);
        MvcResult mvcResult = this.mockMvc.perform(post("/user/login")
                                          .content(toJson)
                                          .contentType(MediaType.APPLICATION_JSON))
                                          .andReturn();
        Assert.assertEquals(message,mvcResult.getResponse().getContentAsString().contains("LOGIN SUCCESSFUL"));
    }


    @Test
    public void givenUserDetailsToLoginUser_WhenInvalidData_ShouldThrowException() throws Exception {
        userLoginDto.emailID="parhiankita@gmail.com";
        userLoginDto.password="Ankita@9713";
        String toJson = new Gson().toJson(userDetailsDto);
        String message = "Invalid Data!!!!! Please Enter Valid Data";
        //when(userService.userLogin(any())).thenThrow(new UserException("Invalid Data!!!!! Please Enter Valid Data", UserException.ExceptionType.INVALID_DATA));
        MvcResult mvcResult = this.mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson)).andReturn();
        Assert.assertEquals(message,new Gson().fromJson(mvcResult.getResponse().getContentAsString(),ResponseDto.class).getMessage());
    }





}
