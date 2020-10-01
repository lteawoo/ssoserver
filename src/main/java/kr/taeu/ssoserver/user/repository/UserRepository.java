package kr.taeu.ssoserver.user.repository;

import kr.taeu.ssoserver.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositorySupport {
}
