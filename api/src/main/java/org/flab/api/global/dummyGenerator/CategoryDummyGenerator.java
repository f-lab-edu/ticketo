package org.flab.api.global.dummyGenerator;

import org.flab.api.domain.category.dto.CategoryListResponse;
import org.flab.api.domain.category.dto.CategoryResponse;
import org.flab.api.domain.category.dto.SubCategoryResponse;

import java.util.List;

public class CategoryDummyGenerator {

    public static CategoryListResponse generateDummyCategoryListResponse() {

        List<SubCategoryResponse> concertSubCategoryList = List.of(
                new SubCategoryResponse(1, "내한공연")
                , new SubCategoryResponse(2, "락/메탈")
                , new SubCategoryResponse(3, "팬미팅/팬클럽")
                , new SubCategoryResponse(4, "페스티벌"));

        List<SubCategoryResponse> musicalSubCategoryList = List.of(
                new SubCategoryResponse(5, "오리지널/내한")
                , new SubCategoryResponse(2, "라이선스")
                , new SubCategoryResponse(3, "창작 뮤지컬")
                , new SubCategoryResponse(4, "넌버벌 퍼포먼스"));


        CategoryResponse concert = new CategoryResponse(1, "콘서트", concertSubCategoryList);
        CategoryResponse musical = new CategoryResponse(2, "뮤지컬", musicalSubCategoryList);

        return new CategoryListResponse(1, List.of(concert, musical));
    }
}
