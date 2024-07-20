package com.example.bookstore.api.service.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookstore.api.controller.book.dto.request.BookCreateRequest;
import com.example.bookstore.api.controller.book.dto.request.BookRentalRequest;
import com.example.bookstore.api.controller.book.dto.response.BookResponse;
import com.example.bookstore.api.controller.book.dto.response.BooksResponse;
import com.example.bookstore.domain.book.Book;
import com.example.bookstore.domain.book.BookRepository;
import com.example.bookstore.domain.book.BookStatus;
import com.example.bookstore.domain.category.Category;
import com.example.bookstore.domain.category.CategoryRepository;
import com.example.bookstore.domain.category.CategoryType;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public List<Book> findByCategory(List<String> request) {
        boolean requestCheck = CategoryType.containsAll(request);

        if(!requestCheck) {
            throw new IllegalArgumentException("요청 중 존재하지 않는 카테코리가 있습니다.");
        }

        List<CategoryType> validCategoryType = CategoryType.of(request);

        List<Category> category = categoryRepository.findAllByTypeIn(validCategoryType);
        
        if(category.isEmpty()) {
        	throw new IllegalArgumentException("해당 카테고리에 책이 존재하지 않습니다.");
        }

        return bookRepository.findAllByCategoryIn(category);
    }

    @Transactional
    public BookResponse createBook(BookCreateRequest request) {
        Category category = categoryRepository.findByType(request.getType());
        
        if(category == null) {
        	throw new IllegalArgumentException("현재 해당 카테고리가 추가되어 있지 않습니다.");
        }

        Book createBook = bookRepository.save(request.ofEntity(category));

        return BookResponse.of(createBook);
    }

    @Transactional
	public BooksResponse rentalBook(@Valid BookRentalRequest request) {
    	
    	List<Book> bookUpdateList = new ArrayList<>();
    	
    	List<Book> findBooks = bookRepository.findAllByTitleInAndBookStatus(request.getBooks(), BookStatus.KEEP);
    	
    	for(String title : request.getBooks()) {
    		Optional<Book> bookToUpdate = findBooks.stream()
    				.filter(book -> book.getTitle().equals(title))
    				.findFirst();
    		
    		if (bookToUpdate.isPresent()) {
    			Book book = bookToUpdate.get(); 
    			book.updateBookStatus();
    			bookUpdateList.add(book);
    		} else {
    			if(request.isCancelCondition()) {
    				throw new IllegalArgumentException("현재 리스트 중 렌탈이 불가능한 책이 존재합니다.");
    			}
    		}
    	}
    	
		return BooksResponse.of(bookUpdateList);
	}
    
}
