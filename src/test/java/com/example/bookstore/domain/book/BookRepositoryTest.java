package com.example.bookstore.domain.book;

import com.example.bookstore.domain.category.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @AfterEach
    void tearDown() {
        bookRepository.deleteAllInBatch();
        categoryRepository.deleteAllInBatch();
    }

    @DisplayName("카테고리 별 책을 모두 조회할 수 있다.")
    @Test
    void findByCategory() {
        // given

        // when

        // then
    }

}