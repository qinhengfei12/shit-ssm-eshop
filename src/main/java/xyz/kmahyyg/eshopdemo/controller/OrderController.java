package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.kmahyyg.eshopdemo.dao.SysItemsDao;
import xyz.kmahyyg.eshopdemo.dao.SysOrdersDao;
import xyz.kmahyyg.eshopdemo.enums.OrderStatusEnum;
import xyz.kmahyyg.eshopdemo.model.SysItems;
import xyz.kmahyyg.eshopdemo.model.SysOrders;
import xyz.kmahyyg.eshopdemo.utils.UserInfoUtil;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

@Controller
public class OrderController {
    @Autowired
    private SysOrdersDao sysOrdersDao;

    @Autowired
    private SysItemsDao sysItemsDao;

    @Autowired
    private UserInfoUtil userInfoUtil;



    /**
     * 查看当前用户的历史订单
     * @param model
     * @return
     */
    @GetMapping("/show/user/order")
    public String showOrderOfCurrentUser(Model model) {
        String currentUserUid = userInfoUtil.getCurrentUserID();
            if (!currentUserUid.isEmpty()){
                List<SysOrders> allOrdersByUser = sysOrdersDao.selectByUserId(currentUserUid);
                model.addAttribute("orders",allOrdersByUser);
            }
        return "userorders";
    }


    @RestController
    @RequestMapping("/api/user")
    public class orderController {
        @PostMapping("/order")
        public String addOrder(@RequestBody String jsonParam) throws Exception {
            String currentUserUid = userInfoUtil.getCurrentUserID();
                if (!currentUserUid.isEmpty()) {
                    SysOrders record = new SysOrders();
                    record.setOid(UUID.randomUUID().toString());
                    record.setStatus(OrderStatusEnum.CREATED);
                    record.setItems(jsonParam);
                    record.setUid(currentUserUid);
                    /**
                     * 从购物车中拿到商品id和数量，计算价格
                     */
                    JSONObject jsonObject = new JSONObject(jsonParam);
                    JSONArray cart = jsonObject.getJSONArray("items");
                    for (int i = 0; i < cart.length(); i++) {
                        JSONObject jsonobj = (JSONObject) cart.get(i);
                        Integer itemid = Integer.valueOf(jsonobj.getString("itemId"));
                        Integer itemno = Integer.valueOf(jsonobj.getString("itemNo"));
                        SysItems items = sysItemsDao.selectById(itemid);
                        BigDecimal price = items.getPrice();
                        BigDecimal finalprice = price.multiply(BigDecimal.valueOf(itemno));
                        record.setFinalPrice(finalprice);
                    }
                    record.setGenTime(new Date().getTime() / 1000);
                    record.setPaymentId(1);
                    record.setDeliveryId(1);
                    sysOrdersDao.insert(record);
                }
            return "OK";
        }

        @DeleteMapping("/order/{orderId}")
        public void delOrder(@PathVariable String orderId, HttpServletResponse response) throws Exception{
            if(orderId != null) {
                sysOrdersDao.deleteOrderByOid(orderId);
            }
            response.sendRedirect("/show/user/order");
        }
    }

}
