package com.example.bookstore.domain.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTypeTest {

    @DisplayName("카테코리 타입이 맞는지 확인후 결과를 반환한다.")
    @Test
    void containAll() {
        // given
        List<String> request = List.of("ACTION");

        // when
        boolean result = CategoryType.containsAll(request);

        // then
        assertThat(result).isTrue();
    }
}
