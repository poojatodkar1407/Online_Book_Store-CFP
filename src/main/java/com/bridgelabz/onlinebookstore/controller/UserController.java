package com.bridgelabz.onlinebookstore.controller;

import com.bridgelabz.onlinebookstore.dto.ResponseDto;
import com.bridgelabz.onlinebookstore.dto.UserLoginDto;
import com.bridgelabz.onlinebookstore.exception.UserException;
import com.bridgelabz.onlinebookstore.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Hello in Online Book Store";
    }


    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody UserLoginDto userLoginDTO, BindingResult bindingResult, HttpServletResponse httpServletResponse) {
        if (bindingResult.hasErrors()) {
            throw new UserException("Invalid Data!!!!! Please Enter Valid Data", UserException.ExceptionType.INVALID_DATA);
        }
        String userLogin = userService.userLogin(userLoginDTO);
        httpServletResponse.setHeader("Authorization", userLogin);
        return new ResponseEntity("LOGIN SUCCESSFUL", HttpStatus.OK);
    }

    @PostMapping("/forget/password")
    public ResponseEntity getResetPassword(@RequestParam("emailID") String emailID, HttpServletRequest httpServletRequest) throws MessagingException {
        String resetPassword = userService.resetPasswordLink(emailID,httpServletRequest.getPathInfo());
        ResponseDto response = new ResponseDto(resetPassword);
        return new ResponseEntity(response,HttpStatus.OK);
    }

}
