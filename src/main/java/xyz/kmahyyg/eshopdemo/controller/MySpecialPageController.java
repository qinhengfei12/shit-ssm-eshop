package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@PreAuthorize("permitAll()")
@Controller
public class MySpecialPageController {
    @GetMapping(value="/")
    public String showIndex(){
        return "static/index";
    }
}