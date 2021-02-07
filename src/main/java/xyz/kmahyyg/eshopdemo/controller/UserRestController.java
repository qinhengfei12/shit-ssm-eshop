package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.kmahyyg.eshopdemo.common.PublicResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/api")
public class UserRestController {
    @PostMapping("/user/register")
    public ResponseEntity<PublicResponse> register(HttpServletRequest request) {
        PublicResponse pr = new PublicResponse(0, "success");
        // currently, we will save the authentication information in HttpSession Object
        // for distributed system, JWT and more precised control should be used.
        return new ResponseEntity<>(pr, HttpStatus.OK);
    }
    @GetMapping("/user/captcha")
    public PublicResponse getCaptcha(HttpServletRequest request){
        PublicResponse pr = new PublicResponse(0, "OK!");

        Instant currTime = Instant.now();
        long upperBound = 99999999;
        long lowerBound = 10000000;
        long rand = (long) (Math.random()*(upperBound - lowerBound + 1) + lowerBound);
        System.out.println(rand);
        return pr;
    }
}
