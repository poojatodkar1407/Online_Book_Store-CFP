package com.bridgelabz.onlinebookstore.controller;

import com.bridgelabz.onlinebookstore.dto.BookDto;
import com.bridgelabz.onlinebookstore.dto.ResponseDto;
import com.bridgelabz.onlinebookstore.model.BookDetailsModel;
import com.bridgelabz.onlinebookstore.services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
@ComponentScan
@EnableAutoConfiguration
public class BookController {

    @Autowired
    IBookService bookService;


    @GetMapping("/welcomeBook")
    public String welcomeBook(){
        return "Hello in Online Book Store Dashboard";
    }

    @PostMapping("/addBook/")
    public ResponseEntity<ResponseDto> addBook(@RequestBody @Valid BookDto bookDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<ResponseDto>(new ResponseDto(bindingResult.getAllErrors().get(0).
                    getDefaultMessage(),"100",null),
                    HttpStatus.BAD_REQUEST);
        }
        BookDetailsModel bookDetailsModel=bookService.addBook(bookDto);
        return new ResponseEntity (new ResponseDto("BOOK ADDED SUCCESFULLY : ",
                "200",bookDetailsModel),
                HttpStatus.CREATED);

    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<BookDetailsModel>> getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.showAllBooks());

    }

    @GetMapping("/getbookindecresing")
    public ResponseEntity<List<BookDetailsModel>> getBooksWithHigherToLower(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.showBookHigherToLower());

    }

    @GetMapping("/getbookdecreasing")
    public ResponseEntity<List<BookDetailsModel>> getBooksWithLowerToHigher(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.showBookLowerToHigher());

    }

    @GetMapping("/getbookpublishingyear")
    public ResponseEntity<List<BookDetailsModel>> getBooksWithNewestLaunch(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.showBookNewLaunch());

    }

    @GetMapping("/books/count")
    public ResponseEntity<ResponseDto> getTotalCount() {
        return new ResponseEntity(bookService.getCountOfBooks(), HttpStatus.OK);
    }


}
