package com.example.demo.messaging;

import com.example.demo.dto.BorrowCreatedEvent;
import com.example.demo.dto.ChapterCreatedEvent;
import com.example.demo.service.BorrowService;
import com.example.demo.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaListeners {

    private final BorrowService borrowService;

    private final CatalogService catalogService;


    @KafkaListener(topics = "library.borrow.v1", groupId = "analytics-group", containerFactory = "factory")
    public void listenerBorrow(@Payload BorrowCreatedEvent borrowCreatedEvent) {
        borrowService.processBorrow(borrowCreatedEvent);
    }

    @KafkaListener(topics = "library.catalog.v1", groupId = "records-group", containerFactory = "factory")
    public void listenerCatalog(@Payload ChapterCreatedEvent chapterCreatedEvent) {
        catalogService.processChapters(chapterCreatedEvent);
    }
}