package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.kmahyyg.eshopdemo.common.PublicResponse;
import xyz.kmahyyg.eshopdemo.enums.ErrorStatusEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class UserRestController {
    private static final String prefixKeyOfPhone = "user:tel:";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/user/register")
    public ResponseEntity<PublicResponse> register(HttpServletRequest request) {
        PublicResponse pr = new PublicResponse(0, "success");
        // currently, we will save information in HttpSession Object
        // for distributed system, JWT and more precised control should be used.
        return new ResponseEntity<>(pr, HttpStatus.OK);
    }
    @PostMapping("/user/captcha")
    public PublicResponse getCaptcha(HttpServletRequest request){
        PublicResponse pr = new PublicResponse(ErrorStatusEnum.SUCCESS.ordinal(), "OK!");
        String phoneNumberStr = request.getParameter("phone");
        try {
            long phoneNumber = Long.parseLong(phoneNumberStr);
            if (phoneNumber < 10000000000L || phoneNumber > 20000000000L){
                throw new NumberFormatException("Not a phone number!");
            }
        } catch (NumberFormatException e){
            pr.setStatus(ErrorStatusEnum.FAILED_USER.ordinal());
            pr.setMessage("Illegal Phone!");
            return pr;
        }
        if (stringRedisTemplate.opsForValue().get(prefixKeyOfPhone + phoneNumberStr) != null) {
            pr.setStatus(ErrorStatusEnum.FAILED_ABUSE.ordinal());
            pr.setMessage("429 Too Many Requests!");
            return pr;
        }
        long upperBound = 99999999;
        long lowerBound = 10000000;
        long rand = (long) (Math.random()*(upperBound - lowerBound + 1) + lowerBound);
        stringRedisTemplate.opsForValue().set(prefixKeyOfPhone + phoneNumberStr, String.valueOf(rand), 2, TimeUnit.MINUTES);
        System.out.println(phoneNumberStr + " -- " + rand);
        return pr;
    }
}
