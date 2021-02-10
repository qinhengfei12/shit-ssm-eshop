package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.kmahyyg.eshopdemo.dao.SysOrdersDao;
import xyz.kmahyyg.eshopdemo.dao.SysUsersDao;

@Controller
public class CartController {
    @Autowired
    private SysOrdersDao sysOrdersDao;

    @Autowired
    private SysUsersDao sysUsersDao;

    @RequestMapping("/show/user/cart")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String showCart(){
        return "cart";
    }

}
