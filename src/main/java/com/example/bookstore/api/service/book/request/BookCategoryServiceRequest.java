package com.example.bookstore.api.service.book.request;

import com.example.bookstore.domain.book.BookStatus;
import com.example.bookstore.domain.category.CategoryType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BookCategoryServiceRequest {

    private List<CategoryType> categoryTypes;

    @Builder
    public BookCategoryServiceRequest(List<CategoryType> categoryTypes) {
        this.categoryTypes = categoryTypes;
    }
}
