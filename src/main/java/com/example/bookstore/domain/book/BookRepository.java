package com.example.bookstore.domain.book;

import com.example.bookstore.domain.category.Category;
import com.example.bookstore.domain.category.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByCategoryIn(List<Category> category);
}
