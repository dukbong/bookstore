package com.example.bookstore.api.controller.book.dto.response;

import com.example.bookstore.domain.book.Book;
import com.example.bookstore.domain.category.Category;
import com.example.bookstore.domain.category.CategoryType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private CategoryType type;

    @Builder
    public BookResponse(Long id, String title, String author, CategoryType type) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.type = type;
    }

    public static BookResponse of(Book createBook) {
        return BookResponse.builder()
                .id(createBook.getId())
                .title(createBook.getTitle())
                .author(createBook.getAuthor())
                .type(createBook.getCategory().getType())
                .build();
    }
}
