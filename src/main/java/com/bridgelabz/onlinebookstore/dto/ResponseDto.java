package com.bridgelabz.onlinebookstore.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ResponseDto {

        private String message;
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
