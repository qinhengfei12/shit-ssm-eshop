package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.kmahyyg.eshopdemo.dao.SysItemsDao;
import xyz.kmahyyg.eshopdemo.model.SysItems;


import java.util.List;

@Controller
public class classifyControler {
    @Autowired
    private SysItemsDao sysItemsDao;

    @GetMapping("/show/classify/{cid:.+}")
    public String classify(@PathVariable String cid, Model model) {
        int intCid = Integer.parseInt(cid);
        List<SysItems> list = sysItemsDao.selectByCateId(intCid);
        model.addAttribute("list",list);
        return "goodsClass";
    }

}
