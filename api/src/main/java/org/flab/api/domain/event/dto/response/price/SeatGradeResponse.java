package org.flab.api.domain.event.dto.response.price;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class SeatGradeResponse {
    private long gradeId;
    private String gradeName;
    private int basicPrice;
    private List<DiscountPolicyResponse> discountPolicies;
}
