package com.example.demo.dto;

import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ChapterBorrowCountDTO {
    private final UUID chapterUuid;
    private final Long borrowCount;
    private final String chapterTitle;
    private final String chapterSecondTitle;
    private final int chapterNumber;
    private final String chapterCoverUrl;

    public ChapterBorrowCountDTO(UUID chapterUuid, Long borrowCount, String chapterTitle, String chapterSecondTitle, int chapterNumber, String chapterCoverUrl) {
        this.chapterUuid = chapterUuid;
        this.borrowCount = borrowCount;
        this.chapterTitle = chapterTitle;
        this.chapterSecondTitle = chapterSecondTitle;
        this.chapterNumber = chapterNumber;
        this.chapterCoverUrl = chapterCoverUrl;
    }
}