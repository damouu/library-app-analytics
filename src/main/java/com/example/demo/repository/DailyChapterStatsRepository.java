package com.example.demo.repository;

import com.example.demo.dto.ChapterBorrowCountDTO;
import com.example.demo.model.DailyChapterStats;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DailyChapterStatsRepository extends JpaRepository<DailyChapterStats, Integer>, JpaSpecificationExecutor<DailyChapterStatsRepository> {

    @Query("""
                SELECT new com.example.demo.dto.ChapterBorrowCountDTO(
                    cp.chapterUuid,
                    SUM(ds.borrowCount),
                    cp.title,
                    cp.secondTitle,
                    cp.chapterNumber,
                    cp.coverArtworkUrl
                )
                FROM DailyChapterStats ds
                JOIN ChapterProjection cp
                    ON cp.chapterUuid = ds.chapterUuid
                WHERE ds.date BETWEEN :startDate AND :endDate
                GROUP BY
                    cp.chapterUuid,
                    cp.title,
                    cp.secondTitle,
                    cp.chapterNumber,
                    cp.coverArtworkUrl
                ORDER BY SUM(ds.borrowCount) DESC
            """)
    List<ChapterBorrowCountDTO> getTopBorrowedChapters(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, Pageable pageable);

}
