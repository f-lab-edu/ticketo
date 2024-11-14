package org.flab.api.domain.user.api;

import org.flab.api.domain.user.dto.UserResponse;
import org.flab.api.global.dummyGenerator.UserDummyGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/me")
public class UserController {

    @GetMapping
    public ResponseEntity<UserResponse> getMe(){
        UserResponse response = UserDummyGenerator.generateUserDummyResponse();
        return ResponseEntity.ok(response);
    }
}
