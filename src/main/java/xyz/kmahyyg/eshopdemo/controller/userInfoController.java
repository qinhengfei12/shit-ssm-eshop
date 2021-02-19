package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xyz.kmahyyg.eshopdemo.dao.SysUsersDao;
import xyz.kmahyyg.eshopdemo.model.SysUsers;
import xyz.kmahyyg.eshopdemo.utils.UserInfoUtil;


@Controller
public class userInfoController {

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
    public String userChange(@RequestParam("rename") String rename,
                             @RequestParam("rephone") String rephone,
                             @RequestParam("readdr") String readdr) {
        SysUsers userSysUsersDao = sysUsersDao.selectByUserId(userInfoUtil.getCurrentUserID());
        if (!rename.isEmpty()){
            userSysUsersDao.setUsername(rename);
        }
        if (!rephone.isEmpty()){
            userSysUsersDao.setPhone(Long.parseLong(rephone));
        }
        if (!readdr.isEmpty()){
            userSysUsersDao.setAddr(readdr);
        }
            System.out.println(userSysUsersDao.toString());


        int ppp = sysUsersDao.updateByUserIdSelective(userSysUsersDao);
        if (ppp == 1){
            System.out.println("OOOOOOOKKKKKKKKK");
        }



        return "redirect:/show/user/home";
    }
}