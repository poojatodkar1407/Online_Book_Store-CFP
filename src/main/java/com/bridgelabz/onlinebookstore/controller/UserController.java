package com.bridgelabz.onlinebookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {

    @GetMapping
    public String welcome(){
        return "Hello in Online Book Store";
    }


}
