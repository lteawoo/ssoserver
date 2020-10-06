package kr.taeu.client.web.controller;

import kr.taeu.client.web.dto.AccessTokenResponse;
import kr.taeu.client.web.service.OAuthClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ClientController {
    private final OAuthClientService oAuthClientService;

    @GetMapping()
    public String index() {
        return "index";
    }

    @GetMapping("/sso")
    public String sso(HttpServletRequest httpServletRequest) {
        String state = UUID.randomUUID().toString();
        httpServletRequest.getSession().setAttribute("state", state);

        StringBuilder builder = new StringBuilder();
        builder.append("redirect:")
                .append("http://localhost:8090/oauth/authorize")
                .append("?response_type=code")
                .append("&client_id=taeu_client")
                .append("&redirect_uri=http://localhost:8091/oauth/callback")
                .append("&state=")
                .append(state);
        return builder.toString();
    }

    @GetMapping("/oauth/callback")
    @ResponseBody
    public String callback(@RequestParam("code") final String code,
                           @RequestParam("state") final String state,
                           HttpSession httpSession) {
        log.info("code: " + code + ", state: " + state);

        // 세션 state 체크
        String oAuthState = (String)httpSession.getAttribute("state");
        log.info(oAuthState);
        httpSession.removeAttribute("state");

        AccessTokenResponse accessTokenResponse = oAuthClientService.requestAccessTokenToAuthServer(code);

        return accessTokenResponse.toString();
    }
}
