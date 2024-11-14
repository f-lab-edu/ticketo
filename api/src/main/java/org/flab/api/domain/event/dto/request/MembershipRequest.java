package org.flab.api.domain.event.dto.request;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class MembershipRequest {
    private String name;
    private String membershipNumber;

    @Override
    public String toString() {
        return "MembershipRequest{" + "name='" + name + '\'' + ", membershipNumber='" + membershipNumber + '\'' + '}';
    }
}
