package com.example.bookstore.api.service.category;

import com.example.bookstore.ApiResponse;
import com.example.bookstore.api.controller.category.dto.request.CategoryCreateRequest;
import com.example.bookstore.api.controller.category.dto.response.CategoryResponse;
import com.example.bookstore.domain.category.Category;
import com.example.bookstore.domain.category.CategoryRepository;
import com.example.bookstore.domain.category.CategoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public CategoryResponse createCategory(CategoryCreateRequest request, LocalDateTime createAt) {
        List<String> targetCategory = List.of(request.getCategory());
        // 해당 카테고리가 작성가능한지 확인
        validateCategory(targetCategory);

        // 해당 카테고리가 이미 있는지 확인 (최대 1개의 검색 결과가 반환된다.)

        CategoryType categoryType = findExistingCategoryByType(CategoryType.of(targetCategory));

        // 카테고리 INSERT
        Category addCategory = Category.builder()
                                .type(categoryType)
                                .createdAt(createAt)
                                .build();

        Category category = categoryRepository.save(addCategory);

        // 반환
        return CategoryResponse.of(category);
    }

    private void validateCategory(List<String> targetCategory) {
        boolean categoryCheck = CategoryType.containsAll(targetCategory);
        if (!categoryCheck) {
            throw new IllegalArgumentException("해당 카테고리 추가를 위해 관리자에게 문의 바랍니다.");
        }
    }

    private CategoryType findExistingCategoryByType(List<CategoryType> targetCategoryType) {
        List<Category> result = categoryRepository.findAllByTypeIn(targetCategoryType);
        if (!result.isEmpty()) {
            throw new IllegalArgumentException("해당 카테고리는 이미 존재합니다.");
        }
        return targetCategoryType.get(0);
    }
}
