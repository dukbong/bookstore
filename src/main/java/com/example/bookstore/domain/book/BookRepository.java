package com.example.bookstore.domain.book;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookstore.domain.category.Category;

public interface BookRepository extends JpaRepository<Book, Long> {

	// SELECT * FROM BOOK WHERE CATEGORY_ID IN ( ... )
    List<Book> findAllByCategoryIn(List<Category> category);

	// SELECT * FROM BOOK WHERE TITLE IN ( ... ) AND BOOKSTATUS = ?
	List<Book> findAllByTitleInAndBookStatus(List<String> books, BookStatus keep);

	// SELECT * FROM BOOK WHERE TITLE = ?
	Optional<Book> findByTitle(String title);
}
