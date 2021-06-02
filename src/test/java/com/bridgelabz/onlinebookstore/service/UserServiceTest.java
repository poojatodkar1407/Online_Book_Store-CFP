package com.bridgelabz.onlinebookstore.service;

//import com.bridgelabz.onlinebookstore.dto.UserDetailsDto;
//import com.bridgelabz.onlinebookstore.dto.UserLoginDto;
//import com.bridgelabz.onlinebookstore.exception.UserException;
//import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
//import com.bridgelabz.onlinebookstore.repository.UserDetailsRepository;
//import com.bridgelabz.onlinebookstore.services.UserService;
//import com.bridgelabz.onlinebookstore.utils.FileProperties;
//import com.bridgelabz.onlinebookstore.utils.Token;
//import org.junit.Assert;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.when;


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
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.mail.MessagingException;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    UserDetailsRepository userRepository;

    @Mock
    UserDetailsRepository userDetailsRepository;

    @InjectMocks
    UserService userService;

    @Mock
    Token jwtToken;

    @MockBean
    FileProperties fileProperties;

    MailService mailService;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserLoginDto userLoginDto;
    private  UserDetailsDto userDetailsDto;
    private UserDetailsModel userDetailsModel;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @BeforeEach
    public void setUp(){

        this.userDetailsDto=new UserDetailsDto();
        this.userLoginDto=new UserLoginDto();
        this.userDetailsModel = new UserDetailsModel();

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

    @Test
    public void givenUserDetails_WhenUserResetThePassword_ShouldReturnMessage() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI3OWRjYWZlMi00Nzk0LTQwMDctYjU4NS00ZjVmNDhkYThiMDQiLCJzdWIiOiJNb3VuYSIsImlhdCI6MTYyMjQ0MzY4NCwiZXhwIjoxNjIyNTQzNjg0fQ.zDCu3Ka9Tslm2wwx8zzXOexVFG-NCfrbOOQohRBjxbg";
        String password = "Attitude@007";
        String message = "Password Has Been Reset";
        when(jwtToken.decodeJWT(token)).thenReturn(UUID.fromString("1ad2bee2-9b32-4e59-966f-c124f1172ef0"));
        when(userRepository.findById(any())).thenReturn(Optional.of(userDetailsModel));
        when(bCryptPasswordEncoder.encode(password)).thenReturn(password);
        when(userRepository.save(any())).thenReturn(userDetailsModel);
        String reset = userService.resetPassword(password, token);
        Assert.assertEquals(message, reset);
    }

    @Test
    public void givenUserDetails_WhenUserResetPassword_ShouldReturnResetPasswordLinkMessage() throws MessagingException {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI3OWRjYWZlMi00Nzk0LTQwMDctYjU4NS00ZjVmNDhkYThiMDQiLCJzdWIiOiJNb3VuYSIsImlhdCI6MTYyMjQ0MzY4NCwiZXhwIjoxNjIyNTQzNjg0fQ.zDCu3Ka9Tslm2wwx8zzXOexVFG-NCfrbOOQohRBjxbg";
        String message ="Reset Password Link Has Been Sent To Your Email Address";
        when(userRepository.findByEmailID("mounamc267@gmail.com")).thenReturn(Optional.of(userDetailsModel));
        when(jwtToken.generateVerificationToken(any())).thenReturn(String.valueOf(userDetailsModel));
        mailService.sendMail(token,"Reset password",userDetailsModel.emailID);
        String user = userService.resetPasswordLink("mounamc267@gmail.com");
        Assert.assertEquals(message,user);
    }

    @Test
    public void givenUserMailId_WhenUserResetPassword_ShouldReturnException() throws  MessagingException {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI3OWRjYWZlMi00Nzk0LTQwMDctYjU4NS00ZjVmNDhkYThiMDQiLCJzdWIiOiJNb3VuYSIsImlhdCI6MTYyMjQ0MzY4NCwiZXhwIjoxNjIyNTQzNjg0fQ.zDCu3Ka9Tslm2wwx8zzXOexVFG-NCfrbOOQohRBjxbg";
        String message ="Reset Password Link Has Been Sent To Your Email Address";
        try {
            when(userRepository.findByEmailID("Ankitha@gmail.com")).thenReturn(Optional.of(userDetailsModel));
            when(jwtToken.generateVerificationToken(any())).thenReturn(String.valueOf(userDetailsModel));
            mailService.sendMail(token, "Reset password", userDetailsModel.emailID);
            String user = userService.resetPasswordLink("Ankitha@gmail.com");
            Assert.assertEquals(message, user);
        }
        catch(Exception e){
            throw new MessagingException(e.getMessage());
        }
    }


    @Test
    public void givenUserToken_WhenUserResetPassword_ShouldReturnException() throws  MessagingException {
        String token = "zDCu3Ka9Tslm2wwx8zzXOexVFG-NCfrbOOQohRBjxbg";
        String message ="Reset Password Link Has Been Sent To Your Email Address";
        try {
            when(userRepository.findByEmailID("mounamc267@gmail.com")).thenReturn(Optional.of(userDetailsModel));
            when(jwtToken.generateVerificationToken(any())).thenReturn(String.valueOf(userDetailsModel));
            mailService.sendMail(token, "Reset password", userDetailsModel.emailID);
            String user = userService.resetPasswordLink("mounamc267@gmail.com");
            Assert.assertEquals(message, user);
        }
        catch(Exception e){
            throw new MessagingException(e.getMessage());
        }
    }
}



//    @Test
//    void givenUserDetailsToLoginUser_WhenUserLoggedIn_ShouldReturnCorrectMessage() {
//        UserDetailsDto userDetailsDto = new UserDetailsDto("Shamal Patil", "shamalpatil1998@gmail.com", "Paju@98", "919665592025", false);
//        UserDetailsModel userDetailsModel = new UserDetailsModel(userDetailsDto);
//        userDetailsModel.isVerified = true;
//        UserLoginDto userLoginDto = new UserLoginDto("shamalpatil1998@gmail.com", "Paju@98");
//        userDetailsModel.userId = 1;
//        when(userRepository.findByEmailID(any())).thenReturn(java.util.Optional.of(userDetailsModel));
//        when(bCryptPasswordEncoder.matches(any(), any())).thenReturn(true);
//        when(fileProperties.getJwtExpirationMs()).thenReturn(86400000);
//        when(tokenGenerator.generateToken(anyInt(), anyInt())).thenReturn("token1");
//        String token = userService.userLogin(userLoginDto);
//        Assert.assertEquals("token1", token);
//    }
//    @Test
//    void givenUserDetailsToLoginUser_WhenUserLoggedIn_ShouldReturnCorrectMessage() {
//        UserDetailsDto userDetailsDto = new UserDetailsDto("shamalpatil1998@gmail.com");
//        UserDetailsModel userDetailsModel = new UserDetailsModel(userDetailsDto);
//        userDetailsModel.isVerified = true;
//        UserLoginDto userLoginDto = new UserLoginDto("shamalpatil1998@gmail.com", "Pajusham@98");
//        userDetailsModel.userId = 6e764082-7d66-443a-9882-e9d7f0fcb662;
//        when(userRepository.findByEmailID(any())).thenReturn(java.util.Optional.of(userDetailsModel));
//        when(bCryptPasswordEncoder.matches(any(), any())).thenReturn(true);
//        when(fileProperties.getJwtExpirationMs()).thenReturn(86400000);
//        when(Token.generateLoginToken(anyInt(), anyInt())).thenReturn("token1");
//        String token = userService.userLogin(userLoginDto);
//        Assert.assertEquals("token1", token);
//    }

//    @Test
//    void givenUserDetailsToLoginUser_WhenIncorrectPasswordEntered_ShouldThrowException() {
//        UserDetailsModel userDetailsModel = new UserDetailsModel(userLoginDto);
//        userDetailsModel.isVerified = true;
//        try {
//
//            when(userRepository.findByEmailID(userLoginDto.email)).thenReturn(java.util.Optional.of(userDetailsModel));
//            when(bCryptPasswordEncoder.matches(userLoginDto.password, userDetailsModel.password)).thenReturn(false);
//            userService.userLogin(userLoginDto);
//        } catch (UserException ex) {
//            Assert.assertEquals(UserException.ExceptionType.PASSWORD_INVALID, ex.type);
//        }
//    }

//    @Test
//    void givenUserDetailsToLoginUser_WhenIncorrectPasswordEntered_ShouldThrowException() {
//        UserDetailsDto userDetailsDto = new UserDetailsDto("shamalpatil1998@gmail.com");
//        UserDetailsModel userDetailsModel = new UserDetailsModel(userLoginDto);
//        userDetailsModel.isVerified = true;
//        try {
//
//            when(userRepository.findByEmailID(userLoginDto.email)).thenReturn(java.util.Optional.of(userDetailsModel));
//            when(bCryptPasswordEncoder.matches(userLoginDto.password, userDetailsModel.password)).thenReturn(false);
//            userService.userLogin(userLoginDto);
//        } catch (UserException ex) {
//            Assert.assertEquals(UserException.ExceptionType.PASSWORD_INVALID, ex.type);
//        }
//    }

//    @Test
//    void givenUserDetailsToLoginUser_WhenIncorrectEmailEntered_ShouldThrowException() {
//        try {
//            when(userRepository.findByEmailID(userLoginDto.email)).thenThrow(new UserException("Enter Registered Email", UserException.ExceptionType.EMAIL_NOT_FOUND));
//            userService.userLogin(userLoginDto);
//        } catch (UserException ex) {
//            Assert.assertEquals(UserException.ExceptionType.EMAIL_NOT_FOUND, ex.type);
//        }
//    }
//
//
//
//
//
//
//    @Test
//    void givenUserDetailsToLoginUser_WhenIncorrectEmailEntered_ShouldThrowException() {
//        try {
//            when(userRepository.findByEmailID(userLoginDto.email)).thenThrow(new UserException("Enter Registered Email", UserException.ExceptionType.EMAIL_NOT_FOUND));
//            userService.userLogin(userLoginDto);
//        } catch (UserException ex) {
//            Assert.assertEquals(UserException.ExceptionType.EMAIL_NOT_FOUND, ex.type);
//        }
//    }
//
//
//
//
//    @Test
//    void givenUserDetailsToLoginUser_WhenIncorrectEmailEntered_ShouldThrowException() {
//        try {
//            when(userRepository.findByEmailID(userLoginDto.emailID)).thenThrow(new UserException("Enter Registered Email", UserException.ExceptionType.EMAIL_NOT_FOUND));
//            userService.userLogin(userLoginDto);
//        } catch (UserException ex) {
//            Assert.assertEquals(UserException.ExceptionType.EMAIL_NOT_FOUND, ex.type);
//        }
//    }


