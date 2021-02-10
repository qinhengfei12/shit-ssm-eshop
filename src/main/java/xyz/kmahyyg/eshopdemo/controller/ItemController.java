package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.kmahyyg.eshopdemo.dao.SysItemsDao;
import xyz.kmahyyg.eshopdemo.model.SysItems;
import java.util.List;
import xyz.kmahyyg.eshopdemo.utils.UserInputSanitizer;

@Controller
public class ItemController {
    @Autowired
    private SysItemsDao sysItemsDao;

    @RequestMapping("show/item/detail")
    public String getItemDetail(Model model,Integer itemId){
        SysItems showItem = sysItemsDao.selectById(itemId);
        if(showItem != null){
            model.addAttribute("showItem", showItem);
            return "itemDetail";
        }else{
            return "error";
        }
    }
    @RequestMapping("show/item/search")
    public String searchResult(Model model, String itemName){
        List<SysItems> searchItems = sysItemsDao.selectByItemName(itemName);
        if(searchItems == null){
            return "error";
        }
        model.addAttribute("searchKey", itemName);
        model.addAttribute("searchResult", searchItems);
        return "search";
    }
}
