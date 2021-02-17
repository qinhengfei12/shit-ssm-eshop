package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        //System.out.print("请求信息：\nItemId:"+request.getParameter("ItemId")+"\nItemNum:"+request.getParameter("ItemNum")+"\n");
        if (CurrentItemsList.size() == 0){
            if (Integer.parseInt(request.getParameter("ItemNum"))>0){

                //更新购物车
                SingleItemInCart NewItem = new SingleItemInCart();
                NewItem.setItemId(Integer.parseInt(request.getParameter("ItemId")));
                NewItem.setItemNum(Integer.parseInt(request.getParameter("ItemNum")));
                //以上是新的商品信息

                CurrentItemsList.add(NewItem);
                SingleUserCart NewItems = new SingleUserCart();
                NewItems.setCart(CurrentItemsList);
                SysUserCart NewCart = new SysUserCart();
                NewCart.setItems(NewItems);
                NewCart.setUid(CurrentUserid);
                NewCart.setId(Integer.parseInt(request.getParameter("ItemId")));
                sysUserCartDao.updateByUserId(NewCart);
            }
            else {
                pr.setMessage("Illegal data!");
                pr.setStatus(403);
            }
        }
        else {
            for (int i=0;i<CurrentItemsList.size();i++){
                SingleItemInCart CurrentItem = CurrentItemsList.get(i);
                if (CurrentItem.getItemId() == Integer.parseInt(request.getParameter("ItemId"))){
                    Integer NewItemNum = CurrentItem.getItemNum() + Integer.parseInt(request.getParameter("ItemNum"));
                    if (NewItemNum>0){
                        CurrentItem.setItemNum(NewItemNum);
                        sysUserCartDao.updateByUserId(CurrentCart);
                        break;
                    }
                    else {
                        if (NewItemNum==0){
                            CurrentItemsList.remove(i);
                            sysUserCartDao.updateByUserId(CurrentCart);
                            break;
                        }
                        else {
                            pr.setMessage("Illegal data!");
                            pr.setStatus(403);
                            break;
                        }
                    }

                }
                else {
                    if (i==CurrentItemsList.size()-1){
                        if (Integer.parseInt(request.getParameter("ItemNum"))>0){
                            SingleItemInCart NewItem = new SingleItemInCart();
                            NewItem.setItemId(Integer.parseInt(request.getParameter("ItemId")));
                            NewItem.setItemNum(Integer.parseInt(request.getParameter("ItemNum")));
                            //以上是新的商品信息

                            CurrentItemsList.add(NewItem);
                            SingleUserCart NewItems = new SingleUserCart();
                            NewItems.setCart(CurrentItemsList);
                            SysUserCart NewCart = new SysUserCart();
                            NewCart.setItems(NewItems);
                            NewCart.setUid(CurrentUserid);
                            NewCart.setId(Integer.parseInt(request.getParameter("ItemId")));
                            sysUserCartDao.updateByUserId(NewCart);
                            break;
                        }
                        else {
                            pr.setMessage("Illegal data!");
                            pr.setStatus(403);
                            break;
                        }
                    }
                }
            }
        }
        return new ResponseEntity<>(pr,HttpStatus.OK);
    }
}


