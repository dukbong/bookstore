package com.example.bookstore.domain.book;

import com.example.bookstore.domain.BaseEntity;
import com.example.bookstore.domain.category.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private int views;

    private LocalDateTime rentalAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @Builder
	public Book(String title, String author, int views, Category category, BookStatus bookStatus) {
		this.title = title;
		this.author = author;
		this.views = views;
		this.category = category;
		this.bookStatus = bookStatus;
	}
    
    

}
