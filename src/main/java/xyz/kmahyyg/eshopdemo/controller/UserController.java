package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @PreAuthorize("permitAll()")
    @RequestMapping("/show/user/login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/show/user/register")
    public String toString() { return "register"; }
}
