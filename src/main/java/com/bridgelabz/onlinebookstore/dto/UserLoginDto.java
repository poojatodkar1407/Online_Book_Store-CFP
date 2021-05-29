package com.bridgelabz.onlinebookstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserLoginDto {

    @Pattern(regexp = "/[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,4}/igm",message = "please do enter the valid email id" )
    @javax.validation.constraints.NotNull(message = "Please Do Enter email id!")
    @NotEmpty(message = "Please Do Enter email id!")
    public String emailID;

    @Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
            message = "please enter correct password")
    @NotNull(message = "Please Do Enter Password!")
    @NotEmpty(message = "Please Do  Enter Password!")
    public String password;

    public UserLoginDto(String emailID, String password) {
        this.emailID = emailID;
        this.password = password;
    }
}
