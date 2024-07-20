package com.example.bookstore.api.controller.book.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.example.bookstore.domain.book.Book;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BooksResponse {

	private List<BookResponse> books;
	
	@Builder
	public BooksResponse(List<BookResponse> books) {
		this.books = books;
	}
	
	public static BooksResponse of(List<Book> books) {
		return BooksResponse.builder()
				.books(books.stream().map(book -> BookResponse.of(book)).collect(Collectors.toList()))
				.build();
	}

}
