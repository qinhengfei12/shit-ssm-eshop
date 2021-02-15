package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.kmahyyg.eshopdemo.dao.SysOrdersDao;
import xyz.kmahyyg.eshopdemo.model.SysOrders;
import xyz.kmahyyg.eshopdemo.utils.UserInfoUtil;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private SysOrdersDao sysOrdersDao;

    @Autowired
    private UserInfoUtil userInfoUtil;

//    @RequestMapping("/show/user/order")
//    public String showOrderOfCurrentUser() {
//            String currentUserUid = userInfoUtil.getCurrentUserID();
//            if (!currentUserUid.isEmpty()){
//                List<SysOrders> allOrdersByUser = sysOrdersDao.selectByUserId(currentUserUid);
//            }
//            //TODO: render the orders data in template html
//        return "userorders";
//    }
}
