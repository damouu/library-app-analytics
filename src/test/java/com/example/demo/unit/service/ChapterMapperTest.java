package com.example.demo.unit.service;

import com.example.demo.dto.ChapterCreatedEvent;
import com.example.demo.dto.ChapterCreatedEventData;
import com.example.demo.mapper.ChapterMapper;
import com.example.demo.model.ChapterProjection;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Select.field;

class ChapterMapperTest {

    private final ChapterMapper mapper = new ChapterMapper();

    @Test
    @DisplayName("Should map all fields correctly from event to projection")
    void toEventData_ShouldMapAllFields() {
        String validDate = "2026-06-25";
        ChapterCreatedEvent event = Instancio.of(ChapterCreatedEvent.class).set(field("data"), Instancio.of(ChapterCreatedEventData.class).set(field("publication_date"), validDate).create()).create();
        ChapterProjection projection = mapper.toEventData(event);
        assertThat(projection).isNotNull();
        assertThat(projection.getChapterUuid()).isEqualTo(event.data().chapter_uuid());
        assertThat(projection.getTitle()).isEqualTo(event.data().title());
        assertThat(projection.getSecondTitle()).isEqualTo(event.data().second_title());
        assertThat(projection.getChapterNumber()).isEqualTo(event.data().chapter_number());
        assertThat(projection.getCoverArtworkUrl()).isEqualTo(event.data().cover_artwork_url());
        assertThat(projection.getPublicationDate()).isEqualTo(LocalDate.parse(validDate));
    }
}