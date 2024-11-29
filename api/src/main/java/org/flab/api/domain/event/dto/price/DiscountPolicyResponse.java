package org.flab.api.domain.event.dto.price;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.event.domain.seat.DiscountPolicy;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class DiscountPolicyResponse {

    private long discountPolicyId;
    private String discountPolicyName;
    private int discountPercent;
    private long discountPrice;
    private long price;

    public DiscountPolicyResponse (DiscountPolicy discountPolicy, long basePrice) {
        this.discountPolicyId = discountPolicy.getId();
        this.discountPolicyName = discountPolicy.getName();
        this.discountPercent = discountPolicy.getPercent();
        this.discountPrice = discountPolicy.getDiscountPrice(basePrice);
        this.price = basePrice - discountPrice;
    }
}
