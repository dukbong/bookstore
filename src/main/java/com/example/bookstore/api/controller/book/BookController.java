package com.example.bookstore.api.controller.book;

import com.example.bookstore.ApiResponse;
import com.example.bookstore.api.service.book.BookService;
import com.example.bookstore.domain.book.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/api/v1/books/from/categories")
    public ApiResponse<List<Book>> findByCategory(@RequestParam(name = "categorytype", required = true) List<String> request) {
        return ApiResponse.ok(bookService.findByCategory(request));
    }
}
