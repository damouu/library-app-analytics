package com.example.demo.service;

import com.example.demo.dto.BorrowEventPayload;
import com.example.demo.model.Borrow;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class KafkaPayloadBuilderService {

    public List<Borrow> buildBorrowEntities(BorrowEventPayload booksArrayJson) {
        return booksArrayJson.getData().getNotificationData().getChapters().stream().map(details -> Borrow.builder().borrowDate(LocalDate.parse(booksArrayJson.getData().getNotificationData().getBorrow_start_date())).chapterUUID(details.getChapterUUID()).build()).toList();
    }
}