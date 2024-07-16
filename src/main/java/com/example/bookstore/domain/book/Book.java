package com.example.bookstore.domain.book;

import com.example.bookstore.domain.category.Category;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private int views;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

}
