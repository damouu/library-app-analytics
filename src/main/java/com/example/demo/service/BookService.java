package com.example.demo.service;

import com.example.demo.dto.BorrowEventPayload;
import com.example.demo.dto.ChapterBorrowCountDTO;
import com.example.demo.model.Borrow;
import com.example.demo.repository.BorrowRepository;
import com.example.demo.utils.PaginationUtil;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;


@Data
@Service
public class BookService {

    private final BorrowRepository borrowRepository;

    private final KafkaPayloadBuilderService payloadBuilderService;


    public void borrowBooks(BorrowEventPayload booksArrayJson) {
        List<Borrow> borrows = payloadBuilderService.buildBorrowEntities(booksArrayJson);
        borrowRepository.saveAll(borrows);
    }

    public ResponseEntity<?> topChapters(Map allParams, String period) {
        LocalDate startDate, end, endDate;
        Pageable pageable = PaginationUtil.extractPage(allParams);

        switch (period.toLowerCase()) {
            case "lastweek":
                LocalDate today = LocalDate.now();
                startDate = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).minusWeeks(1);
                end = startDate.plusDays(6);
                break;
            case "lastmonth":
                startDate = LocalDate.from(LocalDate.now().minusMonths(1).withDayOfMonth(1).atStartOfDay());
                endDate = LocalDate.now().minusMonths(1);
                end = endDate.withDayOfMonth(endDate.lengthOfMonth());
                break;
            default:
                LocalDate startOfWeek = LocalDate.now();
                startDate = startOfWeek.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                end = startDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        }

        List<ChapterBorrowCountDTO> topBorrowedChapters = borrowRepository.getTopBorrowedChapters(startDate, end, pageable);
        return ResponseEntity.ok(topBorrowedChapters);
    }

}
