package com.example.bookstore.api.controller.book.dto.request;

import com.example.bookstore.domain.category.CategoryType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BookCategoryRequest {

    @NotNull(message = "카테고리는 필수 입니다.")
    private List<CategoryType> categoryTypes;

    @Builder
    public BookCategoryRequest(List<CategoryType> categoryTypes) {
        this.categoryTypes = categoryTypes;
    }

}
