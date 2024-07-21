package com.example.bookstore.domain.book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.bookstore.IntegrationTestSupport;
import com.example.bookstore.domain.category.Category;
import com.example.bookstore.domain.category.CategoryRepository;
import com.example.bookstore.domain.category.CategoryType;

class BookRepositoryTest extends IntegrationTestSupport {

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
    	Category category1 = Category.builder()
    			.type(CategoryType.ACTION)
    			.build();
    	
    	Category category2 = Category.builder()
    			.type(CategoryType.DRAMA)
    			.build();
    	
    	categoryRepository.saveAll(List.of(category1, category2));
    	
    	Book book1 = Book.builder()
    			.title("test1")
    			.author("A")
    			.rentalCount(0)
    			.category(category1)
    			.bookStatus(BookStatus.KEEP)
    			.build();
    	
    	Book book2 = Book.builder()
    			.title("test2")
    			.author("B")
    			.rentalCount(0)
    			.category(category2)
    			.bookStatus(BookStatus.RENTAL)
    			.build();
    	
    	Book book3 = Book.builder()
    			.title("test3")
    			.author("C")
    			.rentalCount(0)
    			.category(category1)
    			.bookStatus(BookStatus.KEEP)
    			.build();
    	
    	bookRepository.saveAll(List.of(book1, book2, book3));
    	
        // when
    	List<Book> result = bookRepository.findAllByCategoryIn(List.of(category1));
    	
        // then
    	assertThat(result).hasSize(2)
    			.extracting("title", "author")
    			.containsExactlyInAnyOrder(
    				tuple("test1", "A"),
    				tuple("test3", "C")
    			);
    	
    }
    
    @DisplayName("현재 보관 중인 책을 조회할 수 있다.")
    @Test
    void findAllByTitleInAndBookStatus() {
    	// given
    	Category category1 = Category.builder()
    			.type(CategoryType.ACTION)
    			.build();
    	
    	Category category2 = Category.builder()
    			.type(CategoryType.DRAMA)
    			.build();
    	
    	categoryRepository.saveAll(List.of(category1, category2));
    	
    	Book book1 = Book.builder()
    			.title("test1")
    			.author("A")
    			.rentalCount(0)
    			.category(category1)
    			.bookStatus(BookStatus.KEEP)
    			.build();
    	
    	Book book2 = Book.builder()
    			.title("test2")
    			.author("B")
    			.rentalCount(0)
    			.category(category2)
    			.bookStatus(BookStatus.RENTAL)
    			.build();
    	
    	Book book3 = Book.builder()
    			.title("test3")
    			.author("C")
    			.rentalCount(0)
    			.category(category1)
    			.bookStatus(BookStatus.KEEP)
    			.build();
    	
    	bookRepository.saveAll(List.of(book1, book2, book3));
    	
    	// when
    	List<Book> result = bookRepository.findAllByTitleInAndBookStatus(List.of("test1", "test2"), BookStatus.KEEP);
    	
    	// then
    	assertThat(result).hasSize(1)
    	.extracting("title", "author")
    	.containsExactlyInAnyOrder(
    			tuple("test1", "A")
    			);
    	
    }
    
    @DisplayName("현재 보관 중인 책들 중 렌탈 횟수가 10이하인것을 조회할 수 있다.")
    @Test
    void findAllByTitleInAndBookStatusAndRentalCountLessThanEqual() {
    	// given
    	Category category1 = Category.builder()
    			.type(CategoryType.ACTION)
    			.build();
    	
    	Category category2 = Category.builder()
    			.type(CategoryType.DRAMA)
    			.build();
    	
    	categoryRepository.saveAll(List.of(category1, category2));
    	
    	Book book1 = Book.builder()
    			.title("test1")
    			.author("A")
    			.rentalCount(11)
    			.category(category1)
    			.bookStatus(BookStatus.KEEP)
    			.build();
    	
    	Book book2 = Book.builder()
    			.title("test2")
    			.author("B")
    			.rentalCount(2)
    			.category(category2)
    			.bookStatus(BookStatus.RENTAL)
    			.build();
    	
    	Book book3 = Book.builder()
    			.title("test3")
    			.author("C")
    			.rentalCount(1)
    			.category(category1)
    			.bookStatus(BookStatus.KEEP)
    			.build();
    	
    	bookRepository.saveAll(List.of(book1, book2, book3));
    	
    	// when
    	List<Book> result = bookRepository.findAllByTitleInAndBookStatusAndRentalCountLessThanEqual(List.of("test1", "test2", "test3"), BookStatus.KEEP, 10);
    	
    	// then
    	assertThat(result).hasSize(1)
    	.extracting("title", "author")
    	.containsExactlyInAnyOrder(
    			tuple("test3", "C")
    			);
    	
    }

}