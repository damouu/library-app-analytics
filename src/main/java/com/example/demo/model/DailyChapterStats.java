package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"chapterUuid", "date"}), indexes = {@Index(name = "idx_stats_date", columnList = "date"), @Index(name = "idx_stats_chapter_date", columnList = "chapterUuid,date")})
public class DailyChapterStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID chapterUuid;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private long borrowCount;

}