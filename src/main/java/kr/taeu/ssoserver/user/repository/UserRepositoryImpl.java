package kr.taeu.ssoserver.user.repository;

import static kr.taeu.ssoserver.user.domain.QUser.user;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.taeu.ssoserver.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositorySupport {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<User> findByUsername(final String username) {
        User result = queryFactory.selectFrom(user)
                .where(equalsUsername(username))
                .fetchOne();
        return Optional.of(result);
    }

    private BooleanExpression equalsUsername(final String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }

        return user.username.eq(username);
    }
}
