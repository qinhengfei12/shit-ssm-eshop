package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.kmahyyg.eshopdemo.dao.SysOrdersDao;
import xyz.kmahyyg.eshopdemo.dao.SysUsersDao;
import xyz.kmahyyg.eshopdemo.model.SysOrders;
import xyz.kmahyyg.eshopdemo.security.UserInfo;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private SysOrdersDao sysOrdersDao;

    @Autowired
    private SysUsersDao sysUsersDao;

    @RequestMapping("/show/user/order")
    public String showOrderOfCurrentUser(Model model) {
        UserInfo currentUser = (UserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser != null) {
            String currentUsername = currentUser.getUsername();
            if (!currentUsername.isEmpty()){
                String currentUserUid = sysUsersDao.selectByUserName(currentUsername).getUid();
                List<SysOrders> allOrdersByUser = sysOrdersDao.selectByUserId(currentUserUid);
                model.addAttribute("orders",allOrdersByUser);
            }
        }else{
            return  "login";
        }
        return "userorders";
    }
}
