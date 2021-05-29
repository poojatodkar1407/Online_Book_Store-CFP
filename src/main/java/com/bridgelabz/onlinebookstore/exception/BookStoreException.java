package com.bridgelabz.onlinebookstore.exception;

public class BookStoreException extends RuntimeException{

    public ExceptionTypes exceptionTypes;

    public BookStoreException(ExceptionTypes exceptionTypes){
        this.exceptionTypes=exceptionTypes;
    }



    public enum ExceptionTypes {
        USER_ALREADY_PRESENT("user Already present"),
        USER_NOT_FOUND("user not found"),
        INVALID_USER_ID("user id you have given is incorrect"),
        USER_NOT_PRESENT("user is not present in database")
        ;
        public String errorMsg;
        ExceptionTypes(String errorMsg){
            this.errorMsg =errorMsg;
        }



    }

}
