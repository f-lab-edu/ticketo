package org.flab.api.domain.event.dto.seat;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AreaResponse {
    private long areaId;
    private String areaName;
    private int rowCount;
    private List<RowResponse> rows;

}
