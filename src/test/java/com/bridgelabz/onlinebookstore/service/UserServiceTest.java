package com.bridgelabz.onlinebookstore.service;

import com.bridgelabz.onlinebookstore.dto.ResponseDto;
import com.bridgelabz.onlinebookstore.dto.UserDetailsDto;
import com.bridgelabz.onlinebookstore.dto.UserLoginDto;
import com.bridgelabz.onlinebookstore.exception.BookStoreException;
import com.bridgelabz.onlinebookstore.exception.UserException;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import com.bridgelabz.onlinebookstore.repository.UserDetailsRepository;
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
        userDetailsDto.fullName="Yenner";
        userDetailsDto.emailID="yennefer9713@gmail.com";
        userDetailsDto.password="Skyispink@98";
        userDetailsDto.phoneNumber="919665592028";

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

        userDetailsDto.fullName="Yenner";
        userDetailsDto.emailID="yennefer9713@gmail.com";
        userDetailsDto.password="Skyispink@98";
        userDetailsDto.phoneNumber="919665592028";

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


//    @Test
//    void givenUserDetailsToLoginUser_WhenUserLoggedIn_ShouldReturnCorrectMessage() {
//        userLoginDto.emailID="yennefer9713@gmail.com";
//        userLoginDto.password="Skyispink@98";
//        UserDetailsModel userDetailsModel = new UserDetailsModel(userDetailsDto);
//        userDetailsModel.isVerified = true;
//        UserLoginDto userLoginDto = new UserLoginDto("yennefer9713@gmail.com", "Skyispink@98");
//        when(userDetailsRepository.findByEmailID(any())).thenReturn(java.util.Optional.of(userDetailsModel));
//        when(bCryptPasswordEncoder.matches(any(), any())).thenReturn(true);
//        when(fileProperties.getJwtExpirationMs()).thenReturn(86400000);
//        when(jwtToken.generateLoginToken(any())).thenReturn("token1");
//        String token = userService.userLogin(userLoginDto);
//        Assert.assertEquals("token1", token);
//    }

    @Test
    void givenUserDetailsToLoginUser_WhenIncorrectPasswordEntered_ShouldThrowException() {
        userLoginDto.emailID="yennefer9713@gmail.com";
        userLoginDto.password="Skyispink@98";
        UserDetailsModel userDetailsModel = new UserDetailsModel(userDetailsDto);
        userDetailsModel.isVerified = true;
        try {

            when(userDetailsRepository.findByEmailID(userLoginDto.emailID)).thenReturn(java.util.Optional.of(userDetailsModel));
            when(bCryptPasswordEncoder.matches(userLoginDto.password, userDetailsModel.password)).thenReturn(false);
            userService.userLogin(userLoginDto);
        } catch (UserException ex) {
            Assert.assertEquals(UserException.ExceptionType.PASSWORD_INVALID, ex.exceptionType);
        }
    }
    @Test
    void givenUserDetailsToLoginUser_WhenIncorrectEmailEntered_ShouldThrowException() {
        userLoginDto.emailID="yennefer9713@gmail.com";
        userLoginDto.password="Skyispink@98";
        UserDetailsModel userDetailsModel = new UserDetailsModel(userDetailsDto);
        try {
            when(userDetailsRepository.findByEmailID(userLoginDto.emailID)).thenThrow(new UserException( UserException.ExceptionType.EMAIL_NOT_FOUND));
            userService.userLogin(userLoginDto);
        } catch (UserException ex) {
            Assert.assertEquals(UserException.ExceptionType.EMAIL_NOT_FOUND, ex.exceptionType);
        }
    }

    @Test
    public void givenUserDetails_WhenUserResetThePassword_ShouldReturnMessage() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2YjFhY2ZlMy0xNDkxLTQ5N2UtOTUyNi1hZDVlYTA4NjU1ZmYiLCJzdWIiOiJZZW5uZXIiLCJpYXQiOjE2MjI4Mjc0MTMsImV4cCI6MTYyMjkyNzQxM30.TleAMgju7kHBU2jdKA6jm_bHkO3eGt-vq3rTgZwudns";
        String password = "Skyispink@98";
        String message = "Password Has Been Reset";
        when(jwtToken.decodeJWT(token)).thenReturn(UUID.fromString("6b1acfe3-1491-497e-9526-ad5ea08655ff"));
        when(userRepository.findById(any())).thenReturn(Optional.of(userDetailsModel));
        when(bCryptPasswordEncoder.encode(password)).thenReturn(password);
        when(userRepository.save(any())).thenReturn(userDetailsModel);
        String reset = userService.resetPassword(password, token);
        Assert.assertEquals(message, reset);
    }

    @Test
    public void givenUserDetails_WhenUserResetPassword_ShouldReturnResetPasswordLinkMessage() throws MessagingException {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2YjFhY2ZlMy0xNDkxLTQ5N2UtOTUyNi1hZDVlYTA4NjU1ZmYiLCJzdWIiOiJZZW5uZXIiLCJpYXQiOjE2MjI4Mjc0MTMsImV4cCI6MTYyMjkyNzQxM30.TleAMgju7kHBU2jdKA6jm_bHkO3eGt-vq3rTgZwudns";
        String message ="Reset Password Link Has Been Sent To Your Email Address";
        when(userRepository.findByEmailID("yennefer9713@gmail.com")).thenReturn(Optional.of(userDetailsModel));
        when(jwtToken.generateVerificationToken(any())).thenReturn(String.valueOf(userDetailsModel));
        mailService.sendMail(token,"Reset password",userDetailsModel.emailID);
        String user = userService.resetPasswordLink("yennefer971@gmail.com");
        Assert.assertEquals(message,user);
    }

    @Test
    public void givenUserMailId_WhenUserResetPassword_ShouldReturnException() throws  MessagingException {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2YjFhY2ZlMy0xNDkxLTQ5N2UtOTUyNi1hZDVlYTA4NjU1ZmYiLCJzdWIiOiJZZW5uZXIiLCJpYXQiOjE2MjI4Mjc0MTMsImV4cCI6MTYyMjkyNzQxM30.TleAMgju7kHBU2jdKA6jm_bHkO3eGt-vq3rTgZwudns";
        String message ="Reset Password Link Has Been Sent To Your Email Address";
        try {
            when(userRepository.findByEmailID("yennefer9713@gmail.com")).thenReturn(Optional.of(userDetailsModel));
            when(jwtToken.generateVerificationToken(any())).thenReturn(String.valueOf(userDetailsModel));
            mailService.sendMail(token, "Reset password", userDetailsModel.emailID);
            String user = userService.resetPasswordLink("yennefer9713@gmail.com");
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
            when(userRepository.findByEmailID("yennefer9713@gmail.com")).thenReturn(Optional.of(userDetailsModel));
            when(jwtToken.generateVerificationToken(any())).thenReturn(String.valueOf(userDetailsModel));
            mailService.sendMail(token, "Reset password", userDetailsModel.emailID);
            String user = userService.resetPasswordLink("yennefer9713@gmail.com");
            Assert.assertEquals(message, user);
        }
        catch(Exception e){
            throw new MessagingException(e.getMessage());
        }
    }
}
