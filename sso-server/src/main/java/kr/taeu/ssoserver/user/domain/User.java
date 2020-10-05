package kr.taeu.ssoserver.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_BASE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ", updatable = false)
    private Long seq;

    @Column(name = "USERNAME", nullable = false, unique = true, updatable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false, unique = false, updatable = true)
    private String password;

    @Builder
    public User(final String username,
                final String password) {
        this.username = username;
        this.password = password;
    }
}
