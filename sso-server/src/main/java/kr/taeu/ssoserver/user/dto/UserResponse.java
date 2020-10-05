package kr.taeu.ssoserver.user.dto;

import kr.taeu.ssoserver.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponse {
    private String username;

    @Builder
    public UserResponse(final User user) {
        this.username = user.getUsername();
    }
}
