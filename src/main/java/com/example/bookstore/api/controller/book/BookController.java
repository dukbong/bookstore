package com.example.bookstore.api.controller.book;

import com.example.bookstore.api.controller.book.dto.request.BookCategoryServiceRequest;
import com.example.bookstore.api.service.book.BookService;
import com.example.bookstore.domain.book.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    public List<Book> findByCategory(@RequestBody BookCategoryServiceRequest request) {
        return bookService.findByCategory(request.toServiceRequest());
    }
}
