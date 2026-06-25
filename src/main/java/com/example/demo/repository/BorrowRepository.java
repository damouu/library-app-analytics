package com.example.demo.repository;

import com.example.demo.model.DailyChapterStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BorrowRepository extends JpaRepository<DailyChapterStats, Long>, JpaSpecificationExecutor<DailyChapterStats> {

    Optional<DailyChapterStats> findByChapterUuidAndDate(UUID chapterUuid, LocalDate borrowDate);
}
