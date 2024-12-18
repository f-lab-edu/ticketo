package org.flab.api.domain.category.repository;

import org.flab.api.domain.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAll();
    Optional<Category> findById(Long id);
}
