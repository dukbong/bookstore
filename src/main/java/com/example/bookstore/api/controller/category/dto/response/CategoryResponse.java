package com.example.bookstore.api.controller.category.dto.response;

import com.example.bookstore.ApiResponse;
import com.example.bookstore.domain.category.Category;
import com.example.bookstore.domain.category.CategoryType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CategoryResponse {

    private Long id;
    private LocalDateTime createdAt;
    private CategoryType categoryType;

    @Builder
    public CategoryResponse(Long id, LocalDateTime createdAt, CategoryType categoryType) {
        this.id = id;
        this.createdAt = createdAt;
        this.categoryType = categoryType;
    }

    public static CategoryResponse of(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .createdAt(category.getCreatedAt())
                .categoryType(category.getType())
                .build();
    }
}
