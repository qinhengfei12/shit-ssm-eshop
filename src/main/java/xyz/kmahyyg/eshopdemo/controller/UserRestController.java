package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.kmahyyg.eshopdemo.utils.PublicResponse;

import javax.servlet.ServletRequest;

@RestController
public class UserRestController {
    @PostMapping("/api/user/register")
    public PublicResponse register(ServletRequest request){
        PublicResponse pr = new PublicResponse();
        pr.setData("success");
        pr.setMessage("success");
        pr.setStatus(0);
        return pr;
    }
}
