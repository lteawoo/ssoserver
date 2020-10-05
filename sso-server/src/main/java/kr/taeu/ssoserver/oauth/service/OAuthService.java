package kr.taeu.ssoserver.oauth.service;

import kr.taeu.ssoserver.oauth.domain.AccessToken;
import kr.taeu.ssoserver.oauth.repository.AccessTokenRepository;
import kr.taeu.ssoserver.oauth.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthService {
    private final AccessTokenRepository accessTokenRepository;

    private final ClientRepository clientRepository;
}
