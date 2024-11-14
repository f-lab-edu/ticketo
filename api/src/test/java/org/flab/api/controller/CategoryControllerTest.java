package org.flab.api.controller;

import org.flab.api.BaseIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class CategoryControllerTest extends BaseIntegrationTest {

    @Test
    @DisplayName("category 목록 조회 요청")
    public void getCategoryListResponse() throws Exception {
        // given
        String baseUri = "/api/categories";

        // when
        ResultActions resultActions  = mockMvc.perform(get(baseUri)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalCount").value(isA(Number.class)))
                .andExpect(jsonPath("$.categories").isArray())
                .andExpect(jsonPath("$.categories[*].id", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.categories[*].categoryName", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.categories[*].subCategories").isArray())
                .andExpect(jsonPath("$.categories[*].subCategories[*].subCategoryId", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.categories[*].subCategories[*].subCategoryName", everyItem(isA(String.class))));
    }
}
