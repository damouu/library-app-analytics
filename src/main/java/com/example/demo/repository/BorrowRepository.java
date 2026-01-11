package com.example.demo.repository;

import com.example.demo.dto.ChapterBorrowCountDTO;
import com.example.demo.model.Borrow;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Integer>, JpaSpecificationExecutor<Borrow> {

    @Query("SELECT new com.example.demo.dto.ChapterBorrowCountDTO(b.chapterUuID, COUNT(b.id), b.chapterTitle, b.chapterSecondTitle, b.chapterNumber, b.chapterCoverUrl) " + "FROM Borrow b " + "WHERE b.borrowDate BETWEEN :startWeek AND :endWeek " + "GROUP BY b.chapterUuID, b.chapterTitle, b.chapterSecondTitle, b.chapterNumber, b.chapterCoverUrl " + "ORDER BY COUNT(b.id) DESC")
    List<ChapterBorrowCountDTO> getTopBorrowedChapters(@Param("startWeek") LocalDate startWeek, @Param("endWeek") LocalDate endWeek, Pageable pageable);

}
