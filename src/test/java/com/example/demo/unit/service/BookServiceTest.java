package com.example.demo.unit.service;

import com.example.demo.dto.BorrowEventPayload;
import com.example.demo.model.Borrow;
import com.example.demo.repository.BorrowRepository;
import com.example.demo.service.BookService;
import com.example.demo.service.KafkaPayloadBuilderService;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BorrowRepository borrowRepository;

    @InjectMocks
    private BookService bookService;

    @Mock
    private KafkaPayloadBuilderService payloadBuilderService;

    Borrow borrow;
    BorrowEventPayload borrowEventPayload;

    @BeforeEach
    void setUp() {
        borrow = Instancio.create(Borrow.class);
        borrowEventPayload = Instancio.create(BorrowEventPayload.class);
    }

    @Test
    void borrowBooks() {
        List<Borrow> borrowList = List.of(borrow);
        Mockito.when(payloadBuilderService.buildBorrowEntities(borrowEventPayload)).thenReturn(borrowList);
        bookService.borrowBooks(borrowEventPayload);
        verify(borrowRepository, Mockito.times(1)).saveAll(borrowList);
    }


    @Test
    void topChapters_ShouldCoverLastWeekBranch() {
        Map<String, String> params = Map.of("page", "0", "size", "10");
        ResponseEntity<?> response = bookService.topChapters(params, "lastweek");
        assertNotNull(response);
        verify(borrowRepository).getTopBorrowedChapters(any(), any(), any());
    }

    @Test
    void topChapters_ShouldCoverLastMonthBranch() {
        Map<String, String> params = Map.of("page", "0", "size", "10");
        ResponseEntity<?> response = bookService.topChapters(params, "lastmonth");
        assertNotNull(response);
        verify(borrowRepository).getTopBorrowedChapters(any(), any(), any());
    }

    @Test
    void topChapters_ShouldCoverDefaultBranch() {
        Map<String, String> params = Map.of("page", "0", "size", "10");
        ResponseEntity<?> response = bookService.topChapters(params, "");
        assertNotNull(response);
        verify(borrowRepository).getTopBorrowedChapters(any(), any(), any());
    }

}