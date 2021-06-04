package com.bridgelabz.onlinebookstore.controller;

import com.bridgelabz.onlinebookstore.repository.CartRepository;
import com.bridgelabz.onlinebookstore.services.ICartService;
import com.bridgelabz.onlinebookstore.utils.FileProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class CartControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ICartService cartService;

    @MockBean
    public FileProperties fileProperties;

    @MockBean
    CartRepository cartRepository;

    //HttpHeaders httpHeaders = new HttpHeaders();
//    Gson gson = new Gson();

//    @Test
//    public void givenBookDetailsAndCartDetailsToAddInDatabase_WhenAdded_ThenReturnCorrectMessage() throws Exception {
//        String message = "Book Successfully Added To Cart";
//        String jsonString = gson.toJson(message);
//        ResponseDto responseDto = new ResponseDto(message);
//        String jsonResponseDTO = gson.toJson(responseDTO);
//        when(cartService.saveBooksToCart(anyInt(), anyInt(), any())).thenReturn(message);
//        this.mockMvc.perform(post("/cart/2/1")
//                .content(jsonString)
//                .contentType(MediaType.APPLICATION_JSON)
//                .headers(httpHeaders))
//                .andExpect(content().json(jsonResponseDTO));
//    }
}
