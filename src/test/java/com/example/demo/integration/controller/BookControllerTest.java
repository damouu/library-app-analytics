package com.example.demo.integration.controller;

import com.example.demo.controller.BookController;
import com.example.demo.dto.ChapterBorrowCountDTO;
import com.example.demo.enums.AnalyticsPeriod;
import com.example.demo.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

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
    @DisplayName("GET /public/top-chapters - Should return list of top chapters")
    void getTopChapters_ShouldReturnSuccessData() throws Exception {
        UUID chapterUuid = UUID.randomUUID();
        ChapterBorrowCountDTO mockDto = new ChapterBorrowCountDTO(
                chapterUuid,
                42L,
                "Le Premier Chapitre",
                "Sous-titre",
                1,
                "http://example.com/cover.jpg"
        );
        List<ChapterBorrowCountDTO> mockResponse = List.of(mockDto);

        when(bookService.topChapters(any(AnalyticsPeriod.class), any(Pageable.class)))
                .thenReturn(mockResponse);

        mockMvc.perform(get("/public/top-chapters")
                        .param("period", "CURRENT_WEEK")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "id,desc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].chapterUuid").value(chapterUuid.toString()))
                .andExpect(jsonPath("$[0].title").value("Le Premier Chapitre"))
                .andExpect(jsonPath("$[0].borrowCount").value(42));
    }
}