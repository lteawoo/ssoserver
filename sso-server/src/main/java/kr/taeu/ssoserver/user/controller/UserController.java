package kr.taeu.ssoserver.user.controller;

import kr.taeu.ssoserver.user.dto.CreateUserRequest;
import kr.taeu.ssoserver.user.dto.UserResponse;
import kr.taeu.ssoserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserResponse create(@RequestBody final CreateUserRequest createUserRequest) {
        log.info(createUserRequest.toString());
        return new UserResponse(this.userService.create(createUserRequest));
    }
}
