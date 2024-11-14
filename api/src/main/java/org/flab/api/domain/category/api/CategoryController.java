package org.flab.api.domain.category.api;

import org.flab.api.domain.category.dto.CategoryListResponse;
import org.flab.api.global.dummyGenerator.CategoryDummyGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @GetMapping
    public ResponseEntity<CategoryListResponse> getCategoryList () {
        CategoryListResponse response = CategoryDummyGenerator.generateDummyCategoryListResponse();
        return ResponseEntity.ok(response);
    }
}
