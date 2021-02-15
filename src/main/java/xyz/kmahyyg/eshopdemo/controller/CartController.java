package xyz.kmahyyg.eshopdemo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.kmahyyg.eshopdemo.common.PublicResponse;
import xyz.kmahyyg.eshopdemo.dao.SysItemsDao;
import xyz.kmahyyg.eshopdemo.dao.SysUserCartDao;
import xyz.kmahyyg.eshopdemo.dao.SysUsersDao;
import xyz.kmahyyg.eshopdemo.model.SysItems;
import xyz.kmahyyg.eshopdemo.model.SysUserCart;
import xyz.kmahyyg.eshopdemo.security.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {
    @Autowired
    private SysUserCartDao sysUserCartDao;

    @Autowired
    private SysUsersDao sysUsersDao;

    @Autowired
    private SysItemsDao sysItemsDao;

    //return cart page
    @RequestMapping("/show/user/cart")
    public String showCart(Model model) {
        PublicResponse pr = new PublicResponse(0, "success");
        UserInfo currentUser = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser != null) {
            String currentUsername = currentUser.getUsername();
            if (!currentUsername.isEmpty()){
                String currentUserUid = sysUsersDao.selectByUserName(currentUsername).getUid();
                SysUserCart userCart = sysUserCartDao.selectByUserId(currentUserUid);
                try {
                    List ItemIdList = new ArrayList();
                    List ItemNumList = new ArrayList();
                    List ShowCartInfo = new ArrayList();
                    ObjectMapper mapper = new ObjectMapper();
                    String  Items = userCart.getItems();
                    String nodeName = "cart";
                    JsonNode rootNode = mapper.readTree(Items);
                    JsonNode elements = rootNode.get(nodeName);
                    for(int i=0; i<elements.size(); i++){
                        JsonNode object = elements.get(i);
                        JsonNode itemID = object.get("itemID");
                        JsonNode itemNum = object.get("itemNum");
                        int itemId = itemID.asInt();
                        int Num = itemNum.asInt();
                        ItemIdList.add(itemID);
                        ItemNumList.add(itemNum);
                    }
                    for(int j=0;j<ItemIdList.size();j++){
                        Object Id = ItemIdList.get(j);
                        SysItems userCartInfo = sysItemsDao.selectById(Integer.parseInt(Id.toString()));
                        if(userCartInfo == null){
                            return "error";
                        }
                        ShowCartInfo.add(userCartInfo);
                    }
                    model.addAttribute("CartInfo", ShowCartInfo);
                    model.addAttribute("itemNums",ItemIdList);

                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
            //TODO: render the orders data in template html
        }
        return "cart";
    }


//    @RequestMapping("api/cart/showall")
//    @ResponseBody
//    public ResponseEntity<PublicResponse> ShowCartOfCurrentUser() {
//        UserInfo currentUser = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (currentUser != null) {
//            String currentUsername = currentUser.getUsername();
//            if (!currentUsername.isEmpty()){
//                String currentUserUid = sysUsersDao.selectByUserName(currentUsername).getUid();
//                List<SysUserCart> allCartItems = sysUserCartDao.selectByUserId(currentUserUid);
//            }
//            //TODO: render the orders data in template html
//        }
//
//        return null;
//    }

}
