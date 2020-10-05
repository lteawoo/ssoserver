package kr.taeu.ssoserver.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    @GetMapping(value = "/")
    public String home() {
        return "index";
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
    public String loginPage() {
        return "sign/loginPage";
    }
}
