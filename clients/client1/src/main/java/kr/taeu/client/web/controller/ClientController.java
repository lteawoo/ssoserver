package kr.taeu.client.web.controller;

import kr.taeu.client.web.dto.AccessTokenResponse;
import kr.taeu.client.web.service.OAuthClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ClientController {
    private final OAuthClientService oAuthClientService;
    @Value("${taeu.clientId}")
    private String clientId;
    @Value("${taeu.redirectUri}")
    private String redirectUri;
    @GetMapping()
    public String index() {
        return "index";
    }

    @GetMapping("/sso")
    public String sso(HttpServletRequest httpServletRequest) {
        StringBuilder builder = new StringBuilder();
        builder.append("redirect:")
                .append("http://localhost:8090/oauth/authorize")
                .append("?response_type=code")
                .append("&client_id=" + clientId)
                .append("&redirect_uri=" + redirectUri);
        return builder.toString();
    }

    @GetMapping("/oauth/callback")
    @ResponseBody
    public String callback(@RequestParam("code") final String code,
                           final HttpSession httpSession) {
        log.info("code: " + code);

        AccessTokenResponse accessTokenResponse = oAuthClientService.requestAccessTokenToAuthServer(code);

        return accessTokenResponse.toString();
    }
}
