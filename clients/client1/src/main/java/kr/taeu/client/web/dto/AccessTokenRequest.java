package kr.taeu.client.web.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccessTokenRequest {
    private String grantType;
    private String redirectUri;
    private String code;

    @Builder
    public AccessTokenRequest(final String grantType,
                              final String redirectUri,
                              final String code) {
        this.grantType = grantType;
        this.redirectUri = redirectUri;
        this.code = code;
    }
}
