package kr.taeu.ssoserver.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateUserRequest {
    private String username;
    private String password;

    @Builder
    public CreateUserRequest(final String username,
                             final String password) {
        this.username = username;
        this.password = password;
    }
}
