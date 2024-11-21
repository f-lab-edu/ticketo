package org.flab.api.controller;

import org.flab.api.BaseIntegrationTest;
import org.flab.api.domain.category.dto.CategoryListResponse;
import org.flab.api.domain.category.repository.CategoryRepository;
import org.flab.api.global.exception.CustomException;
import org.flab.api.global.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @Test
    @DisplayName("category 목록 조회 요청 - 부모 카테고리 없는 경우 ")
    public void getCategoryListResponseTest() {
        // given
        categoryRepository.deleteCategoryById(1L);

        // when
        try {
            restTemplate.getForEntity(BASE_URI, CategoryListResponse.class);
        } catch(CustomException e) {
            // then
            assertEquals(e.getErrorCode(), ErrorCode.TOP_CATEGORY_NOT_FOUND);
        }
    }
}
