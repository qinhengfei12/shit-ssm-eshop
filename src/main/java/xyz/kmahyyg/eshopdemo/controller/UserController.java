package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @RequestMapping("/show/user/login")
    public String toLogin() {
        return "login";
    }
}
