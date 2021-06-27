package com.bridgelabz.onlinebookstore.exception;

public class AdminException extends RuntimeException{
    public AdminException.ExceptionType exceptionType;

    public AdminException(AdminException.ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }



    public enum ExceptionType {
        EMAIL_NOT_FOUND("Enter Registered Email"),
        PASSWORD_INVALID("Invalid Password!!!Please Enter Correct Password"),
        INVALID_DATA("Please verify your email before proceeding"),
        USER_NOT_FOUND("Admin not found");

        public String error;

        ExceptionType(String errorMsg) { this.error = errorMsg; }

    }
    public AdminException(String message) { super(message); }

}