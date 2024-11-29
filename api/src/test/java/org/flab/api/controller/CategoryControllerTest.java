package org.flab.api.controller;

import org.flab.api.BaseIntegrationTest;
import org.flab.api.domain.category.dto.CategoryListResponse;
import org.flab.api.domain.category.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryControllerTest extends BaseIntegrationTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private final String BASE_URI = "/api/categories";

    @Test
    @DisplayName("category 목록 조회 요청")
    public void getCategoryListResponse() throws Exception {
        // given

        // when
        ResponseEntity<CategoryListResponse> response = restTemplate.getForEntity(BASE_URI, CategoryListResponse.class);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(Objects.requireNonNull(response.getBody()).getCategories());
        assertEquals(response.getBody().getCategories().size(), response.getBody().getTotalCount());

        String jsonString = objectMapper.writeValueAsString(response.getBody());
        objectMapper.readValue(jsonString, CategoryListResponse.class);
    }
}
