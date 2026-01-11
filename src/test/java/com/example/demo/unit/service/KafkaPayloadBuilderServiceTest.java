package com.example.demo.unit.service;

import com.example.demo.dto.BorrowEventPayload;
import com.example.demo.model.Borrow;
import com.example.demo.service.KafkaPayloadBuilderService;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KafkaPayloadBuilderServiceTest {

    private final KafkaPayloadBuilderService service = new KafkaPayloadBuilderService();

    @Test
    void buildBorrowEntities_ShouldMapDeeplyNestedDtoToEntities() {
        BorrowEventPayload payload = Instancio.create(BorrowEventPayload.class);
        payload.getData().getNotificationData().setBorrow_start_date("2024-01-11");
        List<Borrow> results = service.buildBorrowEntities(payload);
        assertNotNull(results);
        assertFalse(results.isEmpty());
        Borrow firstResult = results.get(0);
        assertEquals("2024-01-11", firstResult.getBorrowDate().toString());
        assertNotNull(firstResult.getChapterUuID());
    }
}