package kr.taeu.ssoserver.user.service;

import kr.taeu.ssoserver.user.domain.User;
import kr.taeu.ssoserver.user.dto.CreateUserRequest;
import kr.taeu.ssoserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public User get(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = get(username);

        // 권한부여 임시
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority role = new SimpleGrantedAuthority("ROLE_USER");
        authorities.add(role);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public User create(final CreateUserRequest createUserRequest) {
        final User newUser = User.builder()
                .username(createUserRequest.getUsername())
                .password(createUserRequest.getPassword())
                .build();
        return userRepository.save(newUser);
    }
}
