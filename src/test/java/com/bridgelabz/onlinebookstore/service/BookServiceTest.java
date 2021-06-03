package com.bridgelabz.onlinebookstore.service;

import com.bridgelabz.onlinebookstore.dto.BookDto;
import com.bridgelabz.onlinebookstore.model.BookDetailsModel;
import com.bridgelabz.onlinebookstore.repository.BookRepository;
import com.bridgelabz.onlinebookstore.services.BookService;
import com.bridgelabz.onlinebookstore.utils.FileProperties;
import com.bridgelabz.onlinebookstore.utils.Token;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class BookServiceTest {

    @MockBean
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @MockBean
    FileProperties fileProperties;

    @Mock
    Token jwtToken;


    BookDto bookDto;

//    @Test
//    public void givenBookDetailsToAdd_WhenGetResponse_ShouldReturnBookDetails() {
//        bookDto = new BookDto("Half Girlfriend", "Chetan Bhagat", "Fictional", 2, 300, 2, 2000);
//        BookDetailsModel bookDetailsModel = new BookDetailsModel(bookDto);
//        when(jwtToken.verifyToken(any())).thenReturn(1);
//        when(userRepository.findById(any())).thenReturn(Optional.of(user));
//        when(onlineBookStoreRepository.save(any())).thenReturn(bookDetails);
//        String message = "ADDED SUCCESSFULLY";
//        String saveBook = adminBookStoreService.saveBook("token", bookDTO);
//        Assert.assertEquals(message, saveBook);
//    }

}
