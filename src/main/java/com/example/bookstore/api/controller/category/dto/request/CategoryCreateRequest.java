package com.example.bookstore.api.controller.category.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CategoryCreateRequest {

    @NotBlank(message = "카테고리 이름은 비어있을 수 없습니다.")
    private String category;

    @Builder
    public CategoryCreateRequest(String category) {
        this.category = category;
    }
}
