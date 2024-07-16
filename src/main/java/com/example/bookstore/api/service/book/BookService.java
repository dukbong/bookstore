package com.example.bookstore.api.service.book;

import com.example.bookstore.api.controller.book.dto.request.BookCategoryServiceRequest;
import com.example.bookstore.api.service.book.request.BookServiceRequest;
import com.example.bookstore.domain.book.Book;
import com.example.bookstore.domain.book.BookRepository;
import com.example.bookstore.domain.category.Category;
import com.example.bookstore.domain.category.CategoryRepository;
import com.example.bookstore.domain.category.CategoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public List<Book> findByCategory(BookCategoryServiceRequest request) {
        List<Category> category = categoryRepository.findByTypeIn(request.getCategoryTypes());

        return bookRepository.findAllByCategoryIn(category);
    }

}
