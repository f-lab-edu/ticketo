package org.flab.api.domain.event.dto.show;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ShowListResponse {
    private int totalCount;
    private List<ShowSimpleResponse> shows;
}
