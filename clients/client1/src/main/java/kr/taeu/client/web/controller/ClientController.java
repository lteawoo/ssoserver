package kr.taeu.client.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ClientController {
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
}
