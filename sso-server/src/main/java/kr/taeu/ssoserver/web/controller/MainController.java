package kr.taeu.ssoserver.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class MainController {
    @GetMapping(value = "/")
    @ResponseBody
    public String home(HttpSession httpSession) {
        httpSession.setAttribute("name", "testTaeu");
        return httpSession.getId() + "\nHello Redis " + httpSession.getAttribute("name");
    }

    @GetMapping(value = "/private")
    public String privatePage(final Principal principal, final Model model) {
        model.addAttribute("user", principal);
        return "private";
    }

    @GetMapping(value = "/public")
    public String publicPage() {
        return "public";
    }

    @GetMapping(value = "/login-page")
    public String loginPage(HttpSession httpSession) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("================================================");
        log.info(auth.toString());
        log.info("session {}", httpSession.getId());
        log.info("================================================");
        return "sign/loginPage";
    }
}
