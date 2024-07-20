package com.example.bookstore.api.controller.book.dto.request;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookRentalRequest {

	@NotEmpty(message = "책 제목(들)은 필수 입력사항입니다.")
	private List<String> books;
	
	private boolean cancelCondition;
	
	@Builder
	public BookRentalRequest(List<String> books, boolean cancelCondition) {
		this.books = books;
		this.cancelCondition = cancelCondition;
	}

}
