package com.example.bookstore.api.service.category;

import com.example.bookstore.IntegrationTestSupport;
import com.example.bookstore.api.controller.category.dto.request.CategoryCreateRequest;
import com.example.bookstore.api.controller.category.dto.response.CategoryResponse;
import com.example.bookstore.domain.category.Category;
import com.example.bookstore.domain.category.CategoryRepository;
import com.example.bookstore.domain.category.CategoryType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CategoryServiceTest extends IntegrationTestSupport {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @AfterEach
    void tearDown() {
        categoryRepository.deleteAllInBatch();
    }

    @DisplayName("카테고리를 추가 할 수 있다.")
    @Test
    void crateCategory() {
        // given
        CategoryCreateRequest categoryCreateRequest = CategoryCreateRequest.builder().category("ACTION").build();
        LocalDateTime now = LocalDateTime.of(2024,6,18,0,0,0);

        // when
        CategoryResponse result = categoryService.createCategory(categoryCreateRequest, now);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getCategoryType()).isEqualTo(CategoryType.ACTION);
        assertThat(result.getCreatedAt()).isEqualTo(now);
    }

    @DisplayName("카테고리 타입으로 정해진 경우만 추가할 수 있다.")
    @Test
    void createCategoryWithCategoryType() {
        // given
        CategoryCreateRequest categoryCreateRequest = CategoryCreateRequest.builder().category("HISTORY").build();
        LocalDateTime now = LocalDateTime.of(2024,6,18,0,0,0);

        // when & then
        assertThatThrownBy(() -> categoryService.createCategory(categoryCreateRequest, now))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 카테고리 추가를 위해 관리자에게 문의 바랍니다.");
    }

    @DisplayName("이미 있는 카테고리는 추가할 수 없다.")
    @Test
    void crateCategoryWithDuplication() {
        // given
        Category category1 = Category.builder()
                .type(CategoryType.ACTION)
                .createdAt(LocalDateTime.of(2024,6,18,0,0,0))
                .build();
        categoryRepository.save(category1);

        CategoryCreateRequest categoryCreateRequest = CategoryCreateRequest.builder().category("ACTION").build();
        LocalDateTime now = LocalDateTime.of(2024,6,18,7,15,0);

        // when & then
        assertThatThrownBy(() -> categoryService.createCategory(categoryCreateRequest, now))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 카테고리는 이미 존재합니다.");
    }
}