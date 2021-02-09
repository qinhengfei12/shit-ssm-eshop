package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ShopContoller {
    @RequestMapping("/show/user/cart")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String showCart(){
        return "cart";
    }
}
