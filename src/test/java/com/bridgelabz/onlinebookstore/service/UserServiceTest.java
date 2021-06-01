package com.bridgelabz.onlinebookstore.service;


import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import com.bridgelabz.onlinebookstore.repository.UserDetailsRepository;
import com.bridgelabz.onlinebookstore.services.UserService;
import com.bridgelabz.onlinebookstore.utils.FileProperties;
import com.bridgelabz.onlinebookstore.utils.MailService;
import com.bridgelabz.onlinebookstore.utils.Token;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.mail.MessagingException;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@AutoConfigureMockMvc
@SpringBootTest
public class UserServiceTest {

    private UserDetailsModel userDetailsModel;

    @Mock
    UserDetailsRepository userRepository;

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

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

@Before
public void setUp(){
    this.userDetailsModel = new UserDetailsModel();
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
    public void givenUserDetails_WhenUserResetPassword_ShouldReturnResetPasswordLinkMessage() throws  MessagingException {
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
