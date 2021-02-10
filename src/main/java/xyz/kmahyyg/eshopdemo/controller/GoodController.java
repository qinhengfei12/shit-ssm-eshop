package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.kmahyyg.eshopdemo.dao.SysItemsDao;
import xyz.kmahyyg.eshopdemo.dao.SysUsersDao;
import xyz.kmahyyg.eshopdemo.model.SysItems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GoodController {
    @Autowired
    private SysItemsDao sysItemsDao;

    @RequestMapping("show/good/detail")
    public String getGoodDetail(ModelMap map,Integer itemId){
        //map.addAttribute("host", itemId);
        //System.out.println("id is:"+itemId);
        SysItems showItem = sysItemsDao.selectById(itemId);
        if(showItem != null){
            map.addAttribute("itemName", showItem.getName());
            map.addAttribute("itemPrice", showItem.getPrice());
            map.addAttribute("itemStatus", showItem.getStatus());
            map.addAttribute("itemImage", showItem.getImage());
            map.addAttribute("itemDesc", showItem.getDescr());
            return "itemDetail";
        }else{
            return "error";
        }
    }
    @RequestMapping("show/good/search")
    public String searchResult(Model model, String itemName){
        List<SysItems> searchItems = sysItemsDao.selectByItemName(itemName);
        if(searchItems == null){
            return "error";
        }
        List resultList = new ArrayList();
        int i;
        for(i=0;i<searchItems.size();i++){
            Map map = new HashMap();
            map.put("itemName",searchItems.get(i).getName());
            map.put("itemPrice",searchItems.get(i).getPrice());
            map.put("itemStatus",searchItems.get(i).getStatus());
            map.put("itemImage",searchItems.get(i).getImage());
            map.put("itemDesc",searchItems.get(i).getDescr());
            resultList.add(map);
        }
        model.addAttribute("searchKey", itemName);
        model.addAttribute("searchResult", resultList);
        return "search";
    }
}
