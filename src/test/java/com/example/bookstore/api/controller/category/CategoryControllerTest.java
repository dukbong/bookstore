package com.example.bookstore.api.controller.category;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.example.bookstore.ControllerTestSupport;
import com.example.bookstore.api.controller.category.dto.request.CategoryCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

class CategoryControllerTest extends ControllerTestSupport {

    @DisplayName("카테고리를 등록할 수 있다.")
    @Test
    void createCategory() throws Exception {
        // given
        CategoryCreateRequest request = CategoryCreateRequest.builder()
                .category("ACTION")
                .build();

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/category/new")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("카테고리를 등록할때 카테고리 파라미터는 필수값이다.")
    @Test
    void createCategoryWithCategoryEmpty() throws Exception {
        // given
        CategoryCreateRequest request = CategoryCreateRequest.builder()
                .category("")
                .build();

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/category/new")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("카테고리 이름은 비어있을 수 없습니다."));
    }

}