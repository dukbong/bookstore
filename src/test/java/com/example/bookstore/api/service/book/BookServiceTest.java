package com.example.bookstore.api.service.book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.bookstore.IntegrationTestSupport;
import com.example.bookstore.api.controller.book.dto.request.BookCategoryServiceRequest;
import com.example.bookstore.domain.book.Book;
import com.example.bookstore.domain.book.BookRepository;
import com.example.bookstore.domain.book.BookStatus;
import com.example.bookstore.domain.category.Category;
import com.example.bookstore.domain.category.CategoryRepository;
import com.example.bookstore.domain.category.CategoryType;

public class BookServiceTest extends IntegrationTestSupport {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BookService bookService;
	
    @AfterEach
    void tearDown() {
        bookRepository.deleteAllInBatch();
        categoryRepository.deleteAllInBatch();
    }
    
    @DisplayName("카테고리가 존재하지 않으면 오류가 발생한다.")
    @Test
    void findByCategoryWithEmpty() {
    	// given
    	Category category1 = Category.builder().type(CategoryType.ACTION).build();
		Category category2 = Category.builder().type(CategoryType.DRAMA).build();
    	
		categoryRepository.saveAll(List.of(category1, category2));
		
		BookCategoryServiceRequest request = BookCategoryServiceRequest.builder()
				.categoryTypes(List.of(CategoryType.COMEDY))
				.build();
		
    	// when && then
		assertThatThrownBy(() -> bookService.findByCategory(request))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("해당 카테고리는 존재하지 않습니다.");
		
    }
	
    @DisplayName("카테고리를 통해 조회할 수 있다.")
    @Test
    void findByCategory() {
    	// given
    	Category category1 = Category.builder().type(CategoryType.ACTION).build();
		Category category2 = Category.builder().type(CategoryType.DRAMA).build();
    	
		categoryRepository.saveAll(List.of(category1, category2));
		
    	Book book1 = Book.builder()
    			.title("test1")
    			.author("A")
    			.views(0)
    			.category(category1)
    			.bookStatus(BookStatus.KEEP)
    			.build();
    	
    	Book book2 = Book.builder()
    			.title("test2")
    			.author("B")
    			.views(0)
    			.category(category2)
    			.bookStatus(BookStatus.RENTAL)
    			.build();
    	
    	Book book3 = Book.builder()
    			.title("test3")
    			.author("C")
    			.views(0)
    			.category(category1)
    			.bookStatus(BookStatus.KEEP)
    			.build();
    	
    	bookRepository.saveAll(List.of(book1, book2, book3));
		
		BookCategoryServiceRequest request = BookCategoryServiceRequest.builder()
				.categoryTypes(List.of(CategoryType.ACTION))
				.build();
		
    	// when
		List<Book> result = bookService.findByCategory(request);
		
		// then
		assertThat(result).hasSize(2)
				.extracting("title", "author")
				.containsExactlyInAnyOrder(
					tuple("test1", "A"),
					tuple("test3", "C")
				);
    }
}
