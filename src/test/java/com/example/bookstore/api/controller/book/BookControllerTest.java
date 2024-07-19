package com.example.bookstore.api.controller.book;

import com.example.bookstore.ControllerTestSupport;
import com.example.bookstore.api.controller.book.dto.request.BookCreateRequest;
import com.example.bookstore.domain.category.CategoryType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class BookControllerTest extends ControllerTestSupport {

    @DisplayName("N개의 카테고리에 해당하는 책을 조회한다.")
    @Test
    void findByCategory() throws Exception {
        // given
        List<String> request = List.of("ACTION");

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/books/from/categories")
                        .param("categorytype", request.toArray(new String[0]))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("N개의 카테고리에 해당하는 책을 조회할때 파라미터는 필수 값이다.")
    @Test
    void findByCategoryWithParamNull() throws Exception {
        // given
        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/books/from/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @DisplayName("책을 추가할 수 있다.")
    @Test
    void createBook() throws Exception {
        // given
        BookCreateRequest request = BookCreateRequest.builder()
                .title("testBook")
                .author("홍길동")
                .type(CategoryType.ACTION)
                .build();

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/book/new")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("책을 추가할때 책 제목은 필수 입력 사항입니다.")
    @Test
    void createBookWithTitleEmpty() throws Exception {
        // given
        BookCreateRequest request = BookCreateRequest.builder()
                .author("홍길동")
                .type(CategoryType.ACTION)
                .build();

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/book/new")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("책 제목은 필수 입력사항입니다."));
    }

    @DisplayName("책을 추가할때 책 저자은 필수 입력 사항입니다.")
    @Test
    void createBookWithAuthorEmpty() throws Exception {
        // given
        BookCreateRequest request = BookCreateRequest.builder()
                .title("testBook")
                .type(CategoryType.ACTION)
                .build();

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/book/new")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("책 저자는 필수 입력사항입니다."));
    }

    @DisplayName("책을 추가할때 책 카테고리는 필수 입력 사항입니다.")
    @Test
    void createBookWithCategoryTypeEmpty() throws Exception {
        // given
        BookCreateRequest request = BookCreateRequest.builder()
                .title("testBook")
                .author("test")
                .build();

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/book/new")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("책의 카테고리는 필수 입력사항입니다."));
    }
}