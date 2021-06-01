package com.bridgelabz.onlinebookstore.service;

import com.bridgelabz.onlinebookstore.dto.ResponseDto;
import com.bridgelabz.onlinebookstore.dto.UserDetailsDto;
import com.bridgelabz.onlinebookstore.dto.UserLoginDto;
import com.bridgelabz.onlinebookstore.exception.BookStoreException;
import com.bridgelabz.onlinebookstore.exception.UserException;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import com.bridgelabz.onlinebookstore.repository.UserDetailsRepository;
import com.bridgelabz.onlinebookstore.services.IUserService;
import com.bridgelabz.onlinebookstore.services.UserService;
import com.bridgelabz.onlinebookstore.utils.FileProperties;
import com.bridgelabz.onlinebookstore.utils.MailService;
import com.bridgelabz.onlinebookstore.utils.Token;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    UserDetailsRepository userDetailsRepository;

    @InjectMocks
    UserService userService;

    @Mock
    Token jwtToken;

    @MockBean
    FileProperties fileProperties;

    @Mock
    MailService mailService;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserLoginDto userLoginDto;
    private  UserDetailsDto userDetailsDto;

    @BeforeEach
    public void setUp(){

        this.userDetailsDto=new UserDetailsDto();
        this.userLoginDto=new UserLoginDto();

    }


    @Test
    void givenUserDetails_WhenAdded_ShouldReturnEqual() throws Exception {
        userDetailsDto.fullName="Ankita Parhi";
        userDetailsDto.emailID="parhiankita@gmail.com";
        userDetailsDto.password="Ankita@9713";
        userDetailsDto.phoneNumber="917077757574";

        UserDetailsModel userDetailsModel = new UserDetailsModel(userDetailsDto);


        try {
            when(userDetailsRepository.findByEmailID(any())).thenReturn(java.util.Optional.of(userDetailsModel));
            when(userDetailsRepository.save(any())).thenReturn(userDetailsModel);
            UserDetailsModel userDetails = userService.addUser(userDetailsDto);
            System.out.println(userDetails);
            String toJson = new Gson().toJson(userDetails);
            System.out.println("the tojson is "+toJson);
            MvcResult mvcResult = this.mockMvc.perform(post("/user/register")
                    .content(toJson)
                    .contentType(MediaType.APPLICATION_JSON)).andReturn();
            String message="USER ADDED SUCCESSFULLY: ";
            Assert.assertEquals(message,new Gson().fromJson(mvcResult.getResponse().getContentAsString(), ResponseDto.class).getMessage());
        }
        catch ( BookStoreException e){
               e.printStackTrace();
        }

    }

    @Test
    void givenUserDetails_WhenUserAlreadyPresent_ShouldThrowException() throws Exception {

        userDetailsDto.fullName="Ankita Parhi";
        userDetailsDto.emailID="parhiankita@gmail.com";
        userDetailsDto.password="Ankita@9713";
        userDetailsDto.phoneNumber="917077757574";

        UserDetailsModel userDetailsModel = new UserDetailsModel(userDetailsDto);
        ;
        String message="user Already present";
        try {
            when(userDetailsRepository.findByEmailID(any())).thenReturn(java.util.Optional.of(userDetailsModel));
            when(userDetailsRepository.save(any())).thenReturn(userDetailsModel);
            UserDetailsModel userDetails = userService.addUser(userDetailsDto);
            System.out.println(userDetails);
            String toJson = new Gson().toJson(userDetails);
            System.out.println("the tojson is "+toJson);
            MvcResult mvcResult = this.mockMvc.perform(post("/user/register")
                    .content(toJson)
                    .contentType(MediaType.APPLICATION_JSON)).andReturn();

        }
        catch ( BookStoreException e){
            Assert.assertEquals(BookStoreException.ExceptionTypes.USER_ALREADY_PRESENT,e.exceptionTypes);
        }

  }


    @Test
    void givenUserDetailsToLoginUser_WhenUserLoggedIn_ShouldReturnCorrectMessage() {
        userLoginDto.emailID="parhiankita@gmail.com";
        userLoginDto.password="Ankita@9713";
        UserDetailsModel userDetailsModel = new UserDetailsModel(userDetailsDto);
        userDetailsModel.isVerified = true;
        UserLoginDto userLoginDto = new UserLoginDto("shamalpatil1998@gmail.com", "Paju@98");
       // userDetailsModel.userId = 1;
        when(userDetailsRepository.findByEmailID(any())).thenReturn(java.util.Optional.of(userDetailsModel));
        when(bCryptPasswordEncoder.matches(any(), any())).thenReturn(true);
        when(fileProperties.getJwtExpirationMs()).thenReturn(86400000);
        when(jwtToken.generateLoginToken(any())).thenReturn("token1");
        String token = userService.userLogin(userLoginDto);
        Assert.assertEquals("token1", token);
    }

    @Test
    void givenUserDetailsToLoginUser_WhenIncorrectPasswordEntered_ShouldThrowException() {
        userLoginDto.emailID="parhiankita@gmail.com";
        userLoginDto.password="Ankita@9713";
        UserDetailsModel userDetailsModel = new UserDetailsModel(userDetailsDto);
        userDetailsModel.isVerified = true;
        try {

            when(userDetailsRepository.findByEmailID(userLoginDto.emailID)).thenReturn(java.util.Optional.of(userDetailsModel));
            when(bCryptPasswordEncoder.matches(userLoginDto.password, userDetailsModel.password)).thenReturn(false);
            userService.userLogin(userLoginDto);
        } catch (UserException ex) {
            Assert.assertEquals(UserException.ExceptionType.PASSWORD_INVALID, ex.type);
        }
    }
    @Test
    void givenUserDetailsToLoginUser_WhenIncorrectEmailEntered_ShouldThrowException() {
        userLoginDto.emailID="parhiankita@gmail.com";
        userLoginDto.password="Ankita@9713";
        UserDetailsModel userDetailsModel = new UserDetailsModel(userDetailsDto);
        try {
            when(userDetailsRepository.findByEmailID(userLoginDto.emailID)).thenThrow(new UserException("Enter Registered Email", UserException.ExceptionType.EMAIL_NOT_FOUND));
            userService.userLogin(userLoginDto);
        } catch (UserException ex) {
            Assert.assertEquals(UserException.ExceptionType.EMAIL_NOT_FOUND, ex.type);
        }
    }











}
