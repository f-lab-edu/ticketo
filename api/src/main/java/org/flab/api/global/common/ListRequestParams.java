package org.flab.api.global.common;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListRequestParams {
    private Integer page = 0;
    private Integer pageSize = 10;
    private String searchOption;
    private String searchValue;
    private String sortOption;
    private String sortDirection;

    @Override
    public String toString() {
        return "ListRequestParams{" + "page=" + page + ", pageSize=" + pageSize + ", searchOption='" + searchOption + '\'' + ", searchValue='" + searchValue + '\'' + ", sortOption='" + sortOption + '\'' + ", sortDirection='" + sortDirection + '\'' + '}';
    }
}
