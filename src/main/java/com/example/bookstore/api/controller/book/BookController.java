package com.example.bookstore.api.controller.book;

import com.example.bookstore.ApiResponse;
import com.example.bookstore.api.controller.book.dto.request.BookCreateRequest;
import com.example.bookstore.api.controller.book.dto.response.BookResponse;
import com.example.bookstore.api.service.book.BookService;
import com.example.bookstore.domain.book.Book;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/api/v1/books/from/categories")
    public ApiResponse<List<Book>> findByCategory(@RequestParam(name = "categorytype", required = true) List<String> request) {
        return ApiResponse.ok(bookService.findByCategory(request));
    }

    @PostMapping("/api/v1/book/new")
    public ApiResponse<BookResponse> createBook(@Valid @RequestBody BookCreateRequest request) {
        return ApiResponse.ok(bookService.createBook(request));
    }
}
