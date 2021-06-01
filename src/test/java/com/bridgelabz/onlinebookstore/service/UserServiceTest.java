package com.bridgelabz.onlinebookstore.service;

import com.bridgelabz.onlinebookstore.dto.UserDetailsDto;
import com.bridgelabz.onlinebookstore.dto.UserLoginDto;
import com.bridgelabz.onlinebookstore.exception.UserException;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import com.bridgelabz.onlinebookstore.repository.UserDetailsRepository;
import com.bridgelabz.onlinebookstore.services.UserService;
import com.bridgelabz.onlinebookstore.utils.FileProperties;
import com.bridgelabz.onlinebookstore.utils.Token;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureWebMvc
public class UserServiceTest {

    @Mock
    UserDetailsRepository userRepository;

    @InjectMocks
    UserService userService;


    @Mock
    Token jwtToken;

    @MockBean
    FileProperties fileProperties;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

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

}
