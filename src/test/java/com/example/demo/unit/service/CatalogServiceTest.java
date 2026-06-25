package com.example.demo.unit.service;

import com.example.demo.dto.ChapterCreatedEvent;
import com.example.demo.mapper.ChapterMapper;
import com.example.demo.model.ChapterProjection;
import com.example.demo.repository.ChapterRepository;
import com.example.demo.service.CatalogService;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatalogServiceTest {

    @Mock
    private ChapterRepository chapterRepository;

    @Mock
    private ChapterMapper chapterMapper;

    @InjectMocks
    private CatalogService catalogService;

    @Test
    @DisplayName("Should map event to projection and save it to repository")
    void processChapters_ShouldSaveProjection_WhenEventReceived() {
        // Arrange
        // On génère des objets réels avec Instancio pour éviter les soucis de "final class"
        ChapterCreatedEvent event = Instancio.create(ChapterCreatedEvent.class);
        ChapterProjection projection = Instancio.create(ChapterProjection.class);

        // Simulation du comportement du mapper
        when(chapterMapper.toEventData(event)).thenReturn(projection);

        // Act
        catalogService.processChapters(event);

        // Assert
        // 1. Vérifie que le mapper a été appelé avec le bon événement
        verify(chapterMapper, times(1)).toEventData(event);

        // 2. Vérifie que le repository a sauvegardé la projection retournée par le mapper
        verify(chapterRepository, times(1)).save(projection);
    }
}