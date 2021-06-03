package com.bridgelabz.onlinebookstore.dto;

import com.bridgelabz.onlinebookstore.model.BookCartDetails;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class ResponseDto {

        public String message;
        private String statusCode;
        private Object object;

        public ResponseDto(String message, String statusCode, Object object) {
            this.message = message;
            this.statusCode = statusCode;
            this.object = object;
        }

        public ResponseDto(Object object){
            this.object = object;
        }

    public ResponseDto(String message,Object object) {
        this.message = message;
        this.object = object;
    }

}
