package com.bridgelabz.onlinebookstore.exception;


import com.bridgelabz.onlinebookstore.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class BookStoreGlobalExceptionHandler {
    @ExceptionHandler(BookStoreException.class)
    public ResponseEntity<ResponseDto> handleAddressBookException(BookStoreException bookStoreException){
        log.error("Exception Occurred : " +bookStoreException.exceptionTypes.errorMsg);
        return new ResponseEntity<ResponseDto>(new ResponseDto(bookStoreException.exceptionTypes.errorMsg,
                                                              null,null),
                                                                HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ResponseDto> userExceptionHandler(UserException userException) {
        log.error("Exception Occurred : " +userException.exceptionType.error);
        return new ResponseEntity<ResponseDto>(new ResponseDto(userException.exceptionType.error, null,null),HttpStatus.BAD_REQUEST);
    }

}
