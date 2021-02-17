package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.kmahyyg.eshopdemo.common.PublicResponse;
import xyz.kmahyyg.eshopdemo.dao.SysItemsDao;
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
    private SysItemsDao sysItemsDao;

    @Autowired
    private UserInfoUtil userInfoUtil;


    @PostMapping("/cart")
    public ResponseEntity<Object> cart(HttpServletRequest request){
        PublicResponse pr = new PublicResponse(0,"Ok");
        String CurrentUserid = userInfoUtil.getCurrentUserID();
        //由于在注册的时候每个用户都有一个空的购物车，所以只需要更新items即可，不需要插入
        //获取数据库中的当前用户的购物车信息
        SysUserCart CurrentCart = sysUserCartDao.selectByUserId(CurrentUserid);
        //获取CurrentCart中的items字段
        SingleUserCart CurrentItems = CurrentCart.getItems();
        List<SingleItemInCart> CurrentItemsList = CurrentItems.getCart();

        //处理获取到的数据
        int ItemId;
        int ItemNum;
        try {
            ItemId = Integer.parseInt(request.getParameter("ItemId"));
            ItemNum = Integer.parseInt(request.getParameter("ItemNum"));
        }catch (NumberFormatException e){
            pr.setStatus(4);
            pr.setMessage("NumberFormatException!Please submit int type data!");
            return new ResponseEntity<>(pr,HttpStatus.BAD_REQUEST);
        }

        if (sysItemsDao.selectById(ItemId) == null){
            pr.setStatus(5);
            pr.setMessage("ItemId not exist!");
            return new ResponseEntity<>(pr,HttpStatus.BAD_REQUEST);
        }

        if (CurrentItemsList.size() == 0){
            if (ItemNum>0){

                //更新购物车
                SingleItemInCart NewItem = new SingleItemInCart();
                NewItem.setItemId(ItemId);
                NewItem.setItemNum(ItemNum);
                //以上是新的商品信息

                CurrentItemsList.add(NewItem);
                SingleUserCart NewItems = new SingleUserCart();
                NewItems.setCart(CurrentItemsList);
                SysUserCart NewCart = new SysUserCart();
                NewCart.setItems(NewItems);
                NewCart.setUid(CurrentUserid);
                sysUserCartDao.updateByUserId(NewCart);
                pr.setStatus(0);
                pr.setMessage("ok");
                return new ResponseEntity<>(pr,HttpStatus.OK);
            }
            else {
                pr.setStatus(3);
                pr.setMessage("Illegal data!");
                return new ResponseEntity<>(pr,HttpStatus.BAD_REQUEST);

            }
        }
        else {
            for (int i=0;i<CurrentItemsList.size();i++){
                SingleItemInCart CurrentItem = CurrentItemsList.get(i);
                if (CurrentItem.getItemId() == ItemId){
                    int NewItemNum = CurrentItem.getItemNum() + ItemNum;
                    if (NewItemNum>0){
                        CurrentItem.setItemNum(NewItemNum);
                        sysUserCartDao.updateByUserId(CurrentCart);
                        pr.setStatus(0);
                        pr.setMessage("ok");
                        return new ResponseEntity<>(pr,HttpStatus.OK);
                    }
                    else {
                        if (NewItemNum==0){
                            CurrentItemsList.remove(i);
                            sysUserCartDao.updateByUserId(CurrentCart);
                            pr.setStatus(0);
                            pr.setMessage("ok");
                            return new ResponseEntity<>(pr,HttpStatus.OK);
                        }
                        else {
                            pr.setStatus(2);
                            pr.setMessage("Illegal data!");
                            return new ResponseEntity<>(pr,HttpStatus.BAD_REQUEST);
                        }
                    }

                }
                else {
                    if (i==CurrentItemsList.size()-1){
                        if (ItemNum>0){
                            SingleItemInCart NewItem = new SingleItemInCart();
                            NewItem.setItemId(ItemId);
                            NewItem.setItemNum(ItemNum);
                            //以上是新的商品信息

                            CurrentItemsList.add(NewItem);
                            SingleUserCart NewItems = new SingleUserCart();
                            NewItems.setCart(CurrentItemsList);
                            SysUserCart NewCart = new SysUserCart();
                            NewCart.setItems(NewItems);
                            NewCart.setUid(CurrentUserid);
                            sysUserCartDao.updateByUserId(NewCart);
                            pr.setStatus(0);
                            pr.setMessage("ok");
                            return new ResponseEntity<>(pr,HttpStatus.OK);
                        }
                        else {
                            pr.setStatus(2);
                            pr.setMessage("Illegal data!");
                            return new ResponseEntity<>(pr,HttpStatus.BAD_REQUEST);
                        }
                    }
                }
            }
        }
        return new ResponseEntity<>(pr,HttpStatus.OK);
    }
}
