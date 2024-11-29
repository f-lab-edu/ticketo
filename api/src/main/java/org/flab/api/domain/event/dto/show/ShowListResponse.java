package org.flab.api.domain.event.dto.show;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ShowListResponse {
    private int totalCount;
    private long eventId;
    private List<ShowSimpleResponse> shows;
}
