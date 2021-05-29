package com.bridgelabz.onlinebookstore.controller;

import com.bridgelabz.onlinebookstore.dto.ResponseDto;
import com.bridgelabz.onlinebookstore.dto.UserDetailsDto;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import com.bridgelabz.onlinebookstore.service.IUserService;
import com.bridgelabz.onlinebookstore.utils.MailService;
import com.bridgelabz.onlinebookstore.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;



    @GetMapping("/welcome")
    public String welcome(){
        return "Hello in Online Book Store";
    }


    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid UserDetailsDto userDetails,
                                                    BindingResult bindingResult){
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

        return new ResponseEntity (HttpStatus.CREATED);

    }



}
