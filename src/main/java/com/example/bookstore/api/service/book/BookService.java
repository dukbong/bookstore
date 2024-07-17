package com.example.bookstore.api.service.book;

import com.example.bookstore.api.controller.book.dto.request.BookCategoryRequest;
import com.example.bookstore.api.service.book.request.BookCategoryServiceRequest;
import com.example.bookstore.domain.book.Book;
import com.example.bookstore.domain.book.BookRepository;
import com.example.bookstore.domain.category.Category;
import com.example.bookstore.domain.category.CategoryRepository;
import com.example.bookstore.domain.category.CategoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

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

}
