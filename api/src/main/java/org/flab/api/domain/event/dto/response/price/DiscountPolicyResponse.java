package org.flab.api.domain.event.dto.response.price;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DiscountPolicyResponse {
    private long discountPolicyId;
    private String discountPolicyName;
    private int discountRate;
    private int price;

    public void setPrice(int basicPrice) {
        double discountMultiplier = (100.0 - discountRate) / 100.0;
        this.price = (int) Math.round(basicPrice * discountMultiplier);
    }
}
