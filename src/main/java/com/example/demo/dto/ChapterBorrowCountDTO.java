package com.example.demo.dto;

import java.io.Serializable;
import java.util.UUID;

public record ChapterBorrowCountDTO(
        UUID chapterUuid,
        Long borrowCount,
        String title,
        String secondTitle,
        Integer chapterNumber,
        String coverArtworkUrl
) implements Serializable {
}