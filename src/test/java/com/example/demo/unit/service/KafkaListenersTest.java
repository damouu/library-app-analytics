package com.example.demo.unit.service;

import com.example.demo.dto.BorrowEventPayload;
import com.example.demo.service.BookService;
import com.example.demo.service.KafkaListeners;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class KafkaListenersTest {


    @Mock
    private BookService bookService;

    @InjectMocks
    private KafkaListeners kafkaListeners;

    @Test
    void listenerBorrow() {
    }

    @Test
    void listenerBorrow_ShouldDelegateToBookService() {
        BorrowEventPayload payload = Instancio.create(BorrowEventPayload.class);
        kafkaListeners.listenerBorrow(payload);
        verify(bookService, times(1)).borrowBooks(payload);
    }
}