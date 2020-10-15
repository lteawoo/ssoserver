package kr.taeu.client.web.service;

import kr.taeu.client.web.dto.AccessTokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthClientService {
    private final RestTemplate restTemplate;
    @Value("${taeu.clientId}")
    private String clientId;
    @Value("${taeu.clientSecret}")
    private String clientSecret;
    @Value("${taeu.redirectUri}")
    private String redirectUri;

    public AccessTokenResponse requestAccessTokenToAuthServer(final String code) {
        StringBuilder uri = new StringBuilder()
                .append("http://localhost:8090/oauth/token")
                .append("?grant_type=authorization_code")
                .append("&redirect_uri=" + redirectUri)
                .append("&code=" + code);

//        final AccessTokenRequest accessTokenRequest = AccessTokenRequest.builder()
//                .grantType("authorization_code")
//                .redirectUri(uri)
//                .code(code)
//                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, clientSecret); // client info 작성

        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<AccessTokenResponse> responseEntity = restTemplate.exchange(uri.toString(), HttpMethod.POST, requestEntity, AccessTokenResponse.class);

        return responseEntity.getBody();
    }
}
