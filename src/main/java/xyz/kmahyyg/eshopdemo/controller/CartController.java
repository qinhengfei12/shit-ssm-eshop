package xyz.kmahyyg.eshopdemo.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.kmahyyg.eshopdemo.common.PublicResponse;
import xyz.kmahyyg.eshopdemo.dao.SysItemsDao;
import xyz.kmahyyg.eshopdemo.dao.SysUserCartDao;
import xyz.kmahyyg.eshopdemo.dao.SysUsersDao;
import xyz.kmahyyg.eshopdemo.enums.ErrorStatusEnum;
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
    @PreAuthorize("hasRole('ROLE_USER')")
    public String showCart(Model model) throws JsonProcessingException {
        PublicResponse pr = new PublicResponse(0, "success");
        UserInfo currentUser = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser != null) {
            String currentUsername = currentUser.getUsername();
            if (!currentUsername.isEmpty()){
                String currentUserUid = sysUsersDao.selectByUserName(currentUsername).getUid();
                SysUserCart userCart = sysUserCartDao.selectByUserId(currentUserUid);
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    String  Items = userCart.getItems();
                    String nodeName = "cart";
                    JsonNode rootNode = mapper.readTree(Items);
                    JsonNode value = rootNode.path(nodeName);
                    List<Map<String, Integer>> cartList = mapper.readValue(value.toString(), new TypeReference<List<Map<String, Integer>>>() {});
                    for(Map<String, Integer> m : cartList){
                        for(Map.Entry<String, Integer> k: m.entrySet()) {
                            if(k.getKey().equals("itemID")){
                                SysItems sysItems= sysItemsDao.selectById(k.getValue());

                            }
                        }
                    }


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
