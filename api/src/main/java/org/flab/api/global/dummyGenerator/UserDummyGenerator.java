package org.flab.api.global.dummyGenerator;

import org.flab.api.domain.user.domain.UserGrade;
import org.flab.api.domain.user.dto.UserResponse;

public class UserDummyGenerator {

    public static UserResponse generateUserDummyResponse(){
        return new UserResponse(12334, "hong", "홍길동", "010-1234-1234", "1234@test.com", "서울시 관악구", UserGrade.GOLD, java.util.List.of(Long.valueOf("1234"), Long.valueOf("12343")));
    }
}
