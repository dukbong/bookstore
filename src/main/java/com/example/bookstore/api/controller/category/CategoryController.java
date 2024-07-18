package com.example.bookstore.api.controller.category;

import com.example.bookstore.ApiResponse;
import com.example.bookstore.api.controller.category.dto.request.CategoryCreateRequest;
import com.example.bookstore.api.controller.category.dto.response.CategoryResponse;
import com.example.bookstore.api.service.category.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/api/v1/category/new")
    public ApiResponse<CategoryResponse> createCategory(@Valid @RequestBody CategoryCreateRequest request) {
        LocalDateTime createAt = LocalDateTime.now();
        return ApiResponse.ok(categoryService.createCategory(request, createAt));
    }

}
