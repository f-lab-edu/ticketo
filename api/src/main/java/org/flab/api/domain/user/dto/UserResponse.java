package org.flab.api.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.user.domain.UserGrade;

import java.util.List;

@AllArgsConstructor
@Getter
public class UserResponse {
    private long userId;
    private String loginId;
    private String userName;
    private String phoneNumber;
    private String email;
    private String address;
    private UserGrade grade;
    private List<Long> preReservationEventIds;
}
