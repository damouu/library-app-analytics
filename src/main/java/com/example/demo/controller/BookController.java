package com.example.demo.controller;

import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/public")
public class BookController {

    private final BookService bookService;

    @GetMapping(path = "/top-chapters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> returnBorrowBooks(@RequestParam Map<String, ?> allParams, @RequestParam(defaultValue = "currentweek") String period) {
        return bookService.topChapters(allParams, period);
    }
}
