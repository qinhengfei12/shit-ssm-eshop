package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.kmahyyg.eshopdemo.common.PublicResponse;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @PreAuthorize("permitAll()")
    @RequestMapping("/show/user/login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/show/user/register")
    public String toRegister() {
        return "register";
    }
    @RequestMapping("/show/user/whoami")
    @ResponseBody
    // This is only for debugging purpose, please remove
    public Object toWhoami() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @RequestMapping("/show/vuln/useraddr")
    @ResponseBody
    // this is only for testing purpose to enhance our ability to do some privilege escalation.
    public ResponseEntity<PublicResponse> showSomeoneAddr(HttpServletRequest request){
        String username = request.getParameter("username");
        String vuln = request.getParameter("vuln");    // if vuln=1, the privilege escalation could be done by not checking identity
        if (!vuln.isEmpty() && vuln.equals("1")){
            // directly return the data of anybody
        } else {
            // check if the username equals to current logged in user.
            // then return
        }
    }

}
