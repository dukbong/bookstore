package com.example.bookstore.domain.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum CategoryType {

    ACTION("액션"),
    COMEDY("코미디"),
    DRAMA("드라마");

    private final String text;

    public static boolean containsAll(List<String> request) {
        for(String requestText : request) {
            try {
                CategoryType.valueOf(requestText);
            } catch (IllegalArgumentException e) {
                return false;
            }
        }
        return true;
    }

    public static List<CategoryType> of(List<String> request) {
        return request.stream()
                .map(CategoryType::valueOf)
                .collect(Collectors.toList());
    }
}
