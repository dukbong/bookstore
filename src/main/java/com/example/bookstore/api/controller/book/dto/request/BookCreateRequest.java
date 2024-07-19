package com.example.bookstore.api.controller.book.dto.request;

import com.example.bookstore.api.controller.book.dto.request.enumValid.annotation.NotBlankCategoryType;
import com.example.bookstore.domain.book.Book;
import com.example.bookstore.domain.book.BookStatus;
import com.example.bookstore.domain.category.Category;
import com.example.bookstore.domain.category.CategoryType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookCreateRequest {

    @NotBlank(message = "책 제목은 필수 입력사항입니다.")
    private String title;
    @NotBlank(message = "책 저자는 필수 입력사항입니다.")
    private String author;
    @NotBlankCategoryType(message = "책의 카테고리는 필수 입력사항입니다.")
    private CategoryType type;

    @Builder
    public BookCreateRequest(String title, String author, CategoryType type) {
        this.title = title;
        this.author = author;
        this.type = type;
    }

    public Book ofEntity(Category category) {
        return Book.builder()
                .title(this.title)
                .author(this.author)
                .views(0)
                .bookStatus(BookStatus.KEEP)
                .category(category)
                .build();
    }
}
