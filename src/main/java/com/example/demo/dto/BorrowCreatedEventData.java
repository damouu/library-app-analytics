package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BorrowCreatedEventData(
        String borrow_start_date,
        List<BorrowedItem> borrowed_items
) {
}