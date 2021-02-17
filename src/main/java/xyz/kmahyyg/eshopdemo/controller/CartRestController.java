package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.kmahyyg.eshopdemo.common.PublicResponse;
import xyz.kmahyyg.eshopdemo.dao.SysUserCartDao;
import xyz.kmahyyg.eshopdemo.model.SingleItemInCart;
import xyz.kmahyyg.eshopdemo.model.SingleUserCart;
import xyz.kmahyyg.eshopdemo.model.SysUserCart;
import xyz.kmahyyg.eshopdemo.utils.UserInfoUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CartRestController {

    @Autowired
    private SysUserCartDao sysUserCartDao;

    @Autowired
    private UserInfoUtil userInfoUtil;

    @DeleteMapping("/cart")
    public ResponseEntity<Object> deleteUserCartItem(HttpServletRequest request){
        PublicResponse pr = new PublicResponse(0, "success");
        try{
            int itemId = Integer.parseInt(request.getParameter("sItemId"));
            String currentUserUid = userInfoUtil.getCurrentUserID();
            if(!currentUserUid.isEmpty()){
                SysUserCart userCart = sysUserCartDao.selectByUserId(currentUserUid);
                SingleUserCart cartLst = userCart.getItems();
                List<SingleItemInCart> itemsInCart = cartLst.getCart();
                for(int i = 0; i<itemsInCart.size(); i++){
                    SingleItemInCart currentItem = itemsInCart.get(i);
                    int currentItemId = currentItem.getItemId();
                    if(currentItemId == itemId){
                        itemsInCart.remove(i);
                        cartLst.setCart(itemsInCart);
                        userCart.setItems(cartLst);
                        if(sysUserCartDao.updateByUserId(userCart) == 1){
                            pr.setMessage("Item Delete Success!");
                            return new ResponseEntity<>(pr, HttpStatus.OK);
                        }else{
                            pr.setMessage("Item Delete Failed!");
                            return new ResponseEntity<>(pr, HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    }
                }
            }
        }catch(NumberFormatException e){
            pr.setMessage("Invalid Data!");
            return new ResponseEntity<>(pr, HttpStatus.BAD_REQUEST);
        }
        pr.setMessage("Item Not Exist");
        return new ResponseEntity<>(pr, HttpStatus.BAD_REQUEST);
    }
}
