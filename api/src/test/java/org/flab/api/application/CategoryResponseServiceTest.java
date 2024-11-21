package org.flab.api.application;

import lombok.RequiredArgsConstructor;
import org.flab.api.BaseUnitTest;
import org.flab.api.domain.category.application.CategoryResponseService;
import org.flab.api.domain.category.domain.Category;
import org.flab.api.domain.category.dto.CategoryListResponse;
import org.flab.api.domain.category.repository.CategoryRepository;
import org.flab.api.global.exception.CustomException;
import org.flab.api.global.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;


@RequiredArgsConstructor
public class CategoryResponseServiceTest extends BaseUnitTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("카테고리 목록 조회")
    public void getCategoryList() {
        // given
        CategoryResponseService sut = new CategoryResponseService(categoryRepository);
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(Category.builder().id(1L).name("콘서트").parent(null).build());
        categoryList.add(Category.builder().id(2L).name("뮤지컬").parent(null).build());
        categoryList.add(Category.builder().id(3L).name("내한공연").parent(categoryList.get(0)).build());
        categoryList.add(Category.builder().id(4L).name("창작뮤지컬").parent(categoryList.get(1)).build());
        given(categoryRepository.findAll()).willReturn(categoryList);

        // when
        CategoryListResponse response = sut.getCategoryListResponse();

        // then
        assertThat(response.getTotalCount()).isEqualTo(2);
        assertThat(response.getCategories().size()).isEqualTo(2);
        assertThat(response.getCategories().getFirst().getSubCategories().size()).isEqualTo(1);
        assertThat(response.getCategories().getLast().getSubCategories().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("카테고리 목록 조회 - 상위 카테고리가 존재하지 않는 경우")
    public void getCategoryListWithException() {
        // given
        CategoryResponseService sut = new CategoryResponseService(categoryRepository);
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(Category.builder().id(1L).name("콘서트").parent(null).build());
        categoryList.add(Category.builder().id(3L).name("내한공연").parent(categoryList.get(0)).build());
        categoryList.add(Category.builder().id(4L).name("창작뮤지컬").parent(categoryList.get(1)).build());
        given(categoryRepository.findAll()).willReturn(categoryList);

        // when
        CustomException exception = assertThrows(CustomException.class, sut::getCategoryListResponse);

        // then
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.TOP_CATEGORY_NOT_FOUND);
    }
}
