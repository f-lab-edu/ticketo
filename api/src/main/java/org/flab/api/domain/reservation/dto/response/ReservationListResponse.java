package org.flab.api.domain.reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ReservationListResponse {
    private int totalCount;
    private List<ReservationSimpleResponse> reservations;
}
