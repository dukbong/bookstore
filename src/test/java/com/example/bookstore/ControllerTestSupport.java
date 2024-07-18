package com.example.bookstore;

import com.example.bookstore.api.controller.book.BookController;
import com.example.bookstore.api.controller.category.CategoryController;
import com.example.bookstore.api.service.book.BookService;
import com.example.bookstore.api.service.category.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
        BookController.class,
        CategoryController.class,
})
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected BookService bookService;

    @MockBean
    protected CategoryService categoryService;
}
