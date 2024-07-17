package com.example.bookstore.api.controller.book;

import com.example.bookstore.ControllerTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

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
}