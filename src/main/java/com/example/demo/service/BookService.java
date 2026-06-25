package com.example.demo.service;

import com.example.demo.dto.ChapterBorrowCountDTO;
import com.example.demo.dto.DateRange;
import com.example.demo.enums.AnalyticsPeriod;
import com.example.demo.repository.DailyChapterStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookService {

    private final DailyChapterStatsRepository dailyChapterStatsRepository;

    @Cacheable(value = "top-chapters", key = "#period.name()")
    public List<ChapterBorrowCountDTO> topChapters(AnalyticsPeriod period, Pageable pageable) {
        DateRange range = period.resolve();
        return dailyChapterStatsRepository.getTopBorrowedChapters(range.startDate(), range.endDate(), pageable);
    }
}
