package kr.taeu.ssoserver.user.controller;

import kr.taeu.ssoserver.user.dto.CreateUserRequest;
import kr.taeu.ssoserver.user.dto.UserResponse;
import kr.taeu.ssoserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public Principal get(final Principal principal) {
        return principal;
    }

    @PostMapping
    public UserResponse create(@RequestBody final CreateUserRequest createUserRequest) {
        log.info(createUserRequest.toString());
        return new UserResponse(this.userService.create(createUserRequest));
    }
}
