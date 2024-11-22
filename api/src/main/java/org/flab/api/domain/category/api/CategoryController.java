package org.flab.api.domain.category.api;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.category.service.CategoryResponseService;
import org.flab.api.domain.category.dto.CategoryListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryResponseService categoryResponseService;

    @GetMapping
    public ResponseEntity<CategoryListResponse> getCategoryList () {
        CategoryListResponse response = categoryResponseService.getCategoryListResponse();
        return ResponseEntity.ok(response);
    }
}
