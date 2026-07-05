package com.example.demo.controller;

import com.example.demo.dto.ChapterBorrowCountDTO;
import com.example.demo.enums.AnalyticsPeriod;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/public")
public class BookController {

    private final BookService bookService;

    @GetMapping(path = "/top-chapters", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<ChapterBorrowCountDTO> returnBorrowBooks(@RequestParam AnalyticsPeriod period, Pageable pageable) {
        return bookService.topChapters(period, pageable);
    }
}
