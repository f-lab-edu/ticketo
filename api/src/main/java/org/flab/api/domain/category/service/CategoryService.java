package org.flab.api.domain.category.service;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.category.domain.CategoryList;
import org.flab.api.domain.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryList getAllCategoryList() {
        return new CategoryList(categoryRepository.findAll());
    }
}
