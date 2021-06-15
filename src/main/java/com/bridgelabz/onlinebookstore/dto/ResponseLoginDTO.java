package com.bridgelabz.onlinebookstore.dto;

public class ResponseLoginDTO {
    public String message;
    private String statusCode;
    private Object object;
    private String objectModel;

    public ResponseLoginDTO(String message, String statusCode, Object object, String objectModel) {
        this.message = message;
        this.statusCode = statusCode;
        this.object = object;
        this.objectModel = objectModel;
    }

    public ResponseLoginDTO(Object object){
        this.object = object;
    }
}
