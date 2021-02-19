package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xyz.kmahyyg.eshopdemo.dao.SysUsersDao;
import xyz.kmahyyg.eshopdemo.model.SysUsers;
import xyz.kmahyyg.eshopdemo.utils.UserInfoUtil;


@Controller
public class UserInfoModController {

    @Autowired
    private UserInfoUtil userInfoUtil;

    @Autowired
    private SysUsersDao sysUsersDao;


    @GetMapping ("/show/user/info")
    public String showInfo(RedirectAttributes redirectAttributes) {
        String currentUserUid = userInfoUtil.getCurrentUserID();
        if (!currentUserUid.isEmpty()){
            SysUsers sysUserInfo = sysUsersDao.selectByUserId(currentUserUid);
            String username = sysUserInfo.getUsername();
            Long phone = sysUserInfo.getPhone();
            String addr = sysUserInfo.getAddr();
            redirectAttributes.addFlashAttribute("username", username);
            redirectAttributes.addFlashAttribute("phone", phone);
            redirectAttributes.addFlashAttribute("addr", addr);
            }
        return "redirect:/show/user/home";
    }
    @PostMapping ("/api/user/change")
    public String userChange(@RequestParam("changeName") String changeName,
                             @RequestParam("changePhone") String changePhone,
                             @RequestParam("changeAddr") String changeAddr,
                             RedirectAttributes redirectAttributes) {
        SysUsers user2Update = sysUsersDao.selectByUserId(userInfoUtil.getCurrentUserID());
        if (!changeName.isEmpty()){
            user2Update.setUsername(changeName);
        }
        if (!changePhone.isEmpty()){
            user2Update.setPhone(Long.parseLong(changePhone));
        }
        if (!changeAddr.isEmpty()){
            user2Update.setAddr(changeAddr);
        }

        if (sysUsersDao.updateByUserIdSelective(user2Update) == 1){
            redirectAttributes.addFlashAttribute("change", "Info changed successfully !");
        }

        return "redirect:/show/user/home";
    }
}
