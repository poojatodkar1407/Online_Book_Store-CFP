package com.bridgelabz.onlinebookstore.controller;

import com.bridgelabz.onlinebookstore.dto.ResponseDto;

import com.bridgelabz.onlinebookstore.dto.UserDetailsDto;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import com.bridgelabz.onlinebookstore.services.IUserService;
import com.bridgelabz.onlinebookstore.dto.UserLoginDto;
import com.bridgelabz.onlinebookstore.exception.UserException;
import com.bridgelabz.onlinebookstore.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;


    Token jwtToken=new Token();
    @GetMapping("/welcome")
    public String welcome(){
        return "Hello in Online Book Store";
    }



    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid UserDetailsDto userDetails, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<ResponseDto>(new ResponseDto(bindingResult.getAllErrors().get(0).
                    getDefaultMessage(),"100",null),
                    HttpStatus.BAD_REQUEST);
        }
        UserDetailsModel userDetailsModel = userService.addUser(userDetails);
        return new ResponseEntity (new ResponseDto("User added succesfully : ",
                "200",userDetailsModel),
                HttpStatus.CREATED);

    }

    @GetMapping("/verify/email/{tokenId}")
    public ResponseEntity verifyEmail(@PathVariable String  tokenId ){
        System.out.println("the token id from responseEntity is : "+tokenId);
        userService.verifyEmail(tokenId);
        return new ResponseEntity ("EMAIL VERIFIED",HttpStatus.OK);



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
    public ResponseEntity<ResponseDto> getResetPassword(@RequestParam("emailID") String emailID) throws MessagingException {
        String link = userService.resetPasswordLink(emailID);
        ResponseDto response = new ResponseDto(link);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/reset/password/")
    public ResponseEntity<ResponseDto> resetPassword(@RequestParam(name = "password") String password,@RequestParam(value = "token",defaultValue = "") String urlToken){
        String resetPassword = userService.resetPassword(password,urlToken);
        ResponseDto response = new ResponseDto(resetPassword);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @PostMapping("/resend/mail")
    public ResponseEntity<ResponseDto> resendMail(@RequestParam("emailID") String emailID) throws MessagingException {

        String link = userService.resetPasswordLink(emailID);
        ResponseDto response = new ResponseDto(link);
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
