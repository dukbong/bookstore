package com.example.bookstore.domain.category;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.bookstore.IntegrationTestSupport;

public class CategoryRepositoryTest extends IntegrationTestSupport {

	@Autowired
	private CategoryRepository categoryRepository;
	
    @AfterEach
    void tearDown() {
        categoryRepository.deleteAllInBatch();
    }
	
	@DisplayName("카테코리 리스트를 통해 카테고리를 조회한다.")
	@Test
	void findByTypeIn() {
		// given
		Category category1 = Category.builder().type(CategoryType.ACTION).build();
		Category category2 = Category.builder().type(CategoryType.DRAMA).build();
		Category category3 = Category.builder().type(CategoryType.COMEDY).build();
		
		categoryRepository.saveAll(List.of(category1, category2, category3));
		
		// when
		List<Category> result = categoryRepository.findAllByTypeIn(List.of(CategoryType.DRAMA, CategoryType.COMEDY));
		
		// then
		assertThat(result).hasSize(2)
				.extracting("type")
				.containsExactlyInAnyOrder(
					CategoryType.DRAMA,
					CategoryType.COMEDY
				);
	}
	
	@DisplayName("카테코리 리스트를 통해 카테고리를 조회할때 없다면 빈 List를 반환한다.")
	@Test
	void findByTypeInWithEmpty() {
		// given
		Category category1 = Category.builder().type(CategoryType.ACTION).build();
		Category category2 = Category.builder().type(CategoryType.DRAMA).build();
		
		categoryRepository.saveAll(List.of(category1, category2));
		
		// when
		List<Category> result = categoryRepository.findAllByTypeIn(List.of(CategoryType.COMEDY));
		
		// then
		assertThat(result).isEmpty();
	}
}
