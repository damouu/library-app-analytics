package com.example.demo.service;

import com.example.demo.dto.BorrowEventPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    private final BookService bookService;

    @Autowired
    public KafkaListeners(BookService bookService) {
        this.bookService = bookService;
    }

    @KafkaListener(topics = "library.borrow.v1", groupId = "analytics-group", containerFactory = "factory")
    public void listenerBorrow(@Payload BorrowEventPayload BorrowEventPayload) {
        bookService.borrowBooks(BorrowEventPayload);
    }
}