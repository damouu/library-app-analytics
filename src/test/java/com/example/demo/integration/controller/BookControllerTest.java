package com.example.demo.integration.controller;

import com.example.demo.controller.BookController;
import com.example.demo.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("GET /public/top-chapters - Should return success data")
    void testReturnBorrowBooks() throws Exception {
        Map<String, String> responseBody = Map.of("data", "success");
        ResponseEntity<Map<String, String>> responseEntity = ResponseEntity.ok(responseBody);


        when(bookService.topChapters(any(), any())).thenReturn((ResponseEntity) responseEntity);


        mockMvc.perform(get("/public/top-chapters").param("page", "0").param("size", "10").param("sort", "id,desc").param("period", "currentweek").contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.data").value("success"));
    }

}