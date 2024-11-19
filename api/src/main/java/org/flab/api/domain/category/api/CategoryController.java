package org.flab.api.domain.category.api;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.category.application.CategoryService;
import org.flab.api.domain.category.dto.CategoryListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<CategoryListResponse> getCategoryList () {
        CategoryListResponse response = categoryService.getCategoryListResponse();
        return ResponseEntity.ok(response);
    }
}
