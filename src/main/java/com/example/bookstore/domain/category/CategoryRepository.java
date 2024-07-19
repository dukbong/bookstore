package com.example.bookstore.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	// SELECT * FROM CATEGORY WHERE TYPE IN ( ... )
    List<Category> findAllByTypeIn(List<CategoryType> categoryTypes);

    // SELECT * FROM CATEGORY WHERE TYPE = ()
    Category findByType(CategoryType categoryType);
}
