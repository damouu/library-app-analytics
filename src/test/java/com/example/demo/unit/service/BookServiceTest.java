package com.example.demo.unit.service;

import com.example.demo.dto.ChapterBorrowCountDTO;
import com.example.demo.enums.AnalyticsPeriod;
import com.example.demo.repository.DailyChapterStatsRepository;
import com.example.demo.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private DailyChapterStatsRepository dailyChapterStatsRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void topChapters_ShouldHandleLastWeek() {
        Pageable pageable = PageRequest.of(0, 10);
        List<ChapterBorrowCountDTO> expectedList = List.of();
        Mockito.when(dailyChapterStatsRepository.getTopBorrowedChapters(any(LocalDate.class), any(LocalDate.class), eq(pageable))).thenReturn(expectedList);
        List<ChapterBorrowCountDTO> result = bookService.topChapters(AnalyticsPeriod.LAST_WEEK, pageable);
        assertNotNull(result);
        assertEquals(expectedList, result);
        verify(dailyChapterStatsRepository).getTopBorrowedChapters(any(LocalDate.class), any(LocalDate.class), eq(pageable));
    }

    @Test
    void topChapters_ShouldHandleLastMonth() {
        Pageable pageable = PageRequest.of(0, 10);
        Mockito.when(dailyChapterStatsRepository.getTopBorrowedChapters(any(LocalDate.class), any(LocalDate.class), eq(pageable))).thenReturn(List.of());
        List<ChapterBorrowCountDTO> result = bookService.topChapters(AnalyticsPeriod.LAST_MONTH, pageable);
        assertNotNull(result);
        verify(dailyChapterStatsRepository).getTopBorrowedChapters(any(LocalDate.class), any(LocalDate.class), eq(pageable));
    }

    @Test
    void topChapters_ShouldHandleCurrentWeek() {
        Pageable pageable = PageRequest.of(0, 10);
        Mockito.when(dailyChapterStatsRepository.getTopBorrowedChapters(any(LocalDate.class), any(LocalDate.class), eq(pageable))).thenReturn(List.of());
        List<ChapterBorrowCountDTO> result = bookService.topChapters(AnalyticsPeriod.CURRENT_WEEK, pageable);
        assertNotNull(result);
        verify(dailyChapterStatsRepository).getTopBorrowedChapters(any(LocalDate.class), any(LocalDate.class), eq(pageable));
    }
}