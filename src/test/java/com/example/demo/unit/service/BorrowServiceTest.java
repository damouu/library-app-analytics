package com.example.demo.unit.service;

import com.example.demo.dto.BorrowCreatedEvent;
import com.example.demo.dto.BorrowCreatedEventData;
import com.example.demo.dto.BorrowedItem;
import com.example.demo.model.DailyChapterStats;
import com.example.demo.repository.BorrowRepository;
import com.example.demo.service.BorrowService;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BorrowServiceTest {

    @Mock
    private BorrowRepository repository2;

    @InjectMocks
    private BorrowService borrowService;

    private UUID chapterUuid1;
    private String borrowDateStr;
    private LocalDate borrowDate;

    @BeforeEach
    void setUp() {
        chapterUuid1 = UUID.randomUUID();
        borrowDateStr = "2026-06-25";
        borrowDate = LocalDate.parse(borrowDateStr);
    }

    @Test
    @DisplayName("Should create new DailyChapterStats when none exists")
    void processBorrow_ShouldCreateNewStats_WhenNotExists() {
        BorrowedItem item1 = Instancio.of(BorrowedItem.class).set(field("chapter_uuid"), chapterUuid1).create();
        BorrowCreatedEventData eventData = Instancio.of(BorrowCreatedEventData.class).set(field("borrow_start_date"), borrowDateStr).set(field("borrowed_items"), List.of(item1)).create();
        BorrowCreatedEvent realEvent = Instancio.of(BorrowCreatedEvent.class).set(field("data"), eventData).create();
        when(repository2.findByChapterUuidAndDate(chapterUuid1, borrowDate)).thenReturn(Optional.empty());
        borrowService.processBorrow(realEvent);
        ArgumentCaptor<DailyChapterStats> statsCaptor = ArgumentCaptor.forClass(DailyChapterStats.class);
        verify(repository2, times(1)).save(statsCaptor.capture());
        DailyChapterStats savedStats = statsCaptor.getValue();
        assertEquals(chapterUuid1, savedStats.getChapterUuid());
        assertEquals(borrowDate, savedStats.getDate());
        assertEquals(1, savedStats.getBorrowCount());
    }


    @Test
    @DisplayName("Should process exactly the number of items provided in the list")
    void processBorrow_ShouldCallSaveForEachItem() {
        List<BorrowedItem> items = Instancio.ofList(BorrowedItem.class).size(3).create();
        String validDate = "2026-06-25";
        BorrowCreatedEvent event = Instancio.of(BorrowCreatedEvent.class).set(field("data"), Instancio.of(BorrowCreatedEventData.class).set(field("borrow_start_date"), validDate).set(field("borrowed_items"), items).create()).create();
        when(repository2.findByChapterUuidAndDate(any(), any())).thenReturn(java.util.Optional.empty());
        borrowService.processBorrow(event);
        verify(repository2, times(3)).save(any(DailyChapterStats.class));
    }
}