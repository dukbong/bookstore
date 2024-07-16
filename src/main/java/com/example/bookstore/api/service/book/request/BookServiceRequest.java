package com.example.bookstore.api.service.book.request;

import com.example.bookstore.domain.book.BookStatus;
import com.example.bookstore.domain.category.CategoryType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookServiceRequest {

    private String title;
    private String author;
    private int views;
    private CategoryType categoryType;
    private BookStatus bookStatus;

    @Builder
    public BookServiceRequest(String title, String author, int views, CategoryType categoryType, BookStatus bookStatus) {
        this.title = title;
        this.author = author;
        this.views = views;
        this.categoryType = categoryType;
        this.bookStatus = bookStatus;
    }
}
