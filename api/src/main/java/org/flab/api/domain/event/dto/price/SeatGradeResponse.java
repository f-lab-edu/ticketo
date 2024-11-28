package org.flab.api.domain.event.dto.price;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.event.domain.seat.Grade;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class SeatGradeResponse {
    private long gradeId;
    private String gradeName;
    private long basePrice;
    private List<DiscountPolicyResponse> discountPolicies;

    public SeatGradeResponse(Grade grade,  List<DiscountPolicyResponse> discountPolicyResponseList) {
        this.gradeId = grade.getId();
        this.gradeName = grade.getName();
        this.basePrice = grade.getBasePrice();
        this.discountPolicies = discountPolicyResponseList;
    }
}
