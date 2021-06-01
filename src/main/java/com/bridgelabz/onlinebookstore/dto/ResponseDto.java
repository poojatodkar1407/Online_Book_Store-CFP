package com.bridgelabz.onlinebookstore.dto;

import lombok.Data;

@Data
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
}
