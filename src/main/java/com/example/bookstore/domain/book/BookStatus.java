package com.example.bookstore.domain.book;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BookStatus {

    RENTAL("대여"),
    KEEP("보관");

    private final String text;
}
