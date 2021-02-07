package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.kmahyyg.eshopdemo.common.PublicResponse;

import javax.servlet.ServletRequest;

@RestController
@RequestMapping("/api")
public class UserRestController {
    @PostMapping("/user/register")
    public ResponseEntity<PublicResponse> register(ServletRequest request) {
        PublicResponse pr = new PublicResponse(0, "success");
        // currently, we will save the authentication information in HttpSession Object
        // for distributed system, JWT and more precised control should be used.
        return new ResponseEntity<>(pr, HttpStatus.OK);
    }
}
