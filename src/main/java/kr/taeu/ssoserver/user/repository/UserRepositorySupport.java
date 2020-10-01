package kr.taeu.ssoserver.user.repository;

import kr.taeu.ssoserver.user.domain.User;

import java.util.Optional;

public interface UserRepositorySupport {
    Optional<User> findByUsername(final String username);
}
