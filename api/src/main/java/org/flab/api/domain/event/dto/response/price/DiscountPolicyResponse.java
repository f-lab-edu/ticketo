package org.flab.api.domain.event.dto.response.price;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DiscountPolicyResponse {
    private long discountPolicyId;
    private String discountPolicyName;
    private int discountRate;
    private int discountPrice;

    public void setDiscountPrice(int basicPrice) {
        double discountMultiplier = (100.0 - discountRate) / 100.0;
        this.discountPrice = (int) Math.round(basicPrice * discountMultiplier);
    }
}
