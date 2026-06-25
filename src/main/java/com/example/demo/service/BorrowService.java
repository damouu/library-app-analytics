package com.example.demo.service;

import com.example.demo.dto.BorrowCreatedEvent;
import com.example.demo.dto.BorrowedItem;
import com.example.demo.model.DailyChapterStats;
import com.example.demo.repository.BorrowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepository repository2;

    @Transactional
    @CacheEvict(value = "top-chapters", allEntries = true)
    public void processBorrow(BorrowCreatedEvent event) {
        LocalDate borrowDate = LocalDate.parse(event.data().borrow_start_date());
        for (BorrowedItem item : event.data().borrowed_items()) {
            UUID chapterUuid = item.chapter_uuid();
            DailyChapterStats stats = repository2.findByChapterUuidAndDate(chapterUuid, borrowDate).orElse(DailyChapterStats.builder().chapterUuid(chapterUuid).date(borrowDate).borrowCount(0).build());
            stats.setBorrowCount(stats.getBorrowCount() + 1);
            repository2.save(stats);
        }
        log.info("saved new borrow");
    }
}
