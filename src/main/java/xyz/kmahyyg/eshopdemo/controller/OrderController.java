package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.kmahyyg.eshopdemo.dao.SysItemsDao;
import xyz.kmahyyg.eshopdemo.dao.SysOrdersDao;
import xyz.kmahyyg.eshopdemo.dao.SysUserCartDao;
import xyz.kmahyyg.eshopdemo.dao.SysUsersDao;
import xyz.kmahyyg.eshopdemo.enums.OrderStatusEnum;
import xyz.kmahyyg.eshopdemo.model.SysItems;
import xyz.kmahyyg.eshopdemo.model.SysOrders;
import xyz.kmahyyg.eshopdemo.model.SysUserCart;
import xyz.kmahyyg.eshopdemo.security.UserInfo;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class OrderController {
    @Autowired
    private SysOrdersDao sysOrdersDao;

    @Autowired
    private SysUsersDao sysUsersDao;

    @Autowired
    private SysUserCartDao sysUserCartDao;

    @Autowired
    private SysItemsDao sysItemsDao;


    /**
     * 查看当前用户的历史订单
     * @param model
     * @return
     */
    @RequestMapping("/show/user/order")
    public String showOrderOfCurrentUser(Model model) {
        UserInfo currentUser = (UserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser != null) {
            String currentUsername = currentUser.getUsername();
            if (!currentUsername.isEmpty()){
                String currentUserUid = sysUsersDao.selectByUserName(currentUsername).getUid();
                List<SysOrders> allOrdersByUser = sysOrdersDao.selectByUserId(currentUserUid);
                model.addAttribute("orders",allOrdersByUser);
            }
        }else{
            return  "login";
        }
        return "userorders";
    }

    /**
     * 根据购物车生成订单
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/show/user/addorder")
    public String addOrderOfCurrentUser(Model model) throws Exception{
        UserInfo currentUser = (UserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(currentUser !=null){
            String currentUsername = currentUser.getUsername();
            if (!currentUsername.isEmpty()){
                String currentUserUid = sysUsersDao.selectByUserName(currentUsername).getUid();
                List<SysUserCart> allUserCartByUser = sysUserCartDao.selectByUserId(currentUserUid);
                SysOrders record = new SysOrders();
                for(SysUserCart sysUserCart:allUserCartByUser){
//                    sysUserCartDao.deleteByUserId(currentUserUid);
                    record.setOid(UUID.randomUUID().toString());
                    record.setStatus(OrderStatusEnum.CREATED);
                    record.setItems(sysUserCart.getItems());
                    record.setUid(currentUserUid);
                    /**
                     * 从购物车中拿到商品id和数量，计算价格
                     */
                    JSONObject jsonObject = new JSONObject(sysUserCart.getItems());
                    JSONArray cart = jsonObject.getJSONArray("cart");
                    for(int i=0;i<cart.length();i++){
                        JSONObject jsonobj = (JSONObject) cart.get(i);
                        Integer itemid = Integer.valueOf(jsonobj.getString("itemId"));
                        Integer itemno = Integer.valueOf(jsonobj.getString("itemNo"));
                        SysItems items = sysItemsDao.selectById(itemid);
                        BigDecimal price = items.getPrice();
                        BigDecimal finalprice = price.multiply(BigDecimal.valueOf(itemno));
                        record.setFinalPrice(finalprice);
                    }
                    record.setGenTime(Long.valueOf(Long.toString(new Date().getTime())));

                    record.setPaymentId(1);
                    record.setDeliveryId(1);
                    sysOrdersDao.insert(record);

                    List<SysOrders> allOrdersByUser = sysOrdersDao.selectByUserId(currentUserUid);
                    model.addAttribute("orders",allOrdersByUser);
                }
            }
        }
        return "userorders";
    }


}
