package com.example.bookstore.domain.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CategoryType {

    ACTION("액션"),
    COMEDY("코미디"),
    DRAMA("드라마");

    private final String text;
}
