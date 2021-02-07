package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.kmahyyg.eshopdemo.common.DeliveryResponse;
import xyz.kmahyyg.eshopdemo.common.PaymentResponse;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SupplyAndPaymentRestContoller {
    @PostMapping("/api/delivery/all")
    public List<DeliveryResponse> showAllDelivery(){
        List<DeliveryResponse> dr = new ArrayList<DeliveryResponse>();
        dr.add(new DeliveryResponse(1, "SF"));
        dr.add(new DeliveryResponse(2,"JD"));
        dr.add(new DeliveryResponse(3,"CN"));
        return dr;
    }

    @PostMapping("/api/payment/all")
    public List<PaymentResponse> showAllPayment(){
        List<PaymentResponse> pr = new ArrayList<PaymentResponse>();
        pr.add(new PaymentResponse(1, "Alipay"));
        pr.add(new PaymentResponse(2,"PayPal"));
        return pr;
    }
}
