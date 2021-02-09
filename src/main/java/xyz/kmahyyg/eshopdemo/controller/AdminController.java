package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {
    @RequestMapping("/show/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showAdmin(){
        return "admin";
    }

    @RequestMapping("/show/vuln/admin")
    @ResponseBody
    public String showVulnAdmin(){
        return "vuln-imadmin";
    }
}