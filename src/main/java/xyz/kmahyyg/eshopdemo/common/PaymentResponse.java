package xyz.kmahyyg.eshopdemo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude()
@Data
public class PaymentResponse {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;

    public PaymentResponse(int id, String name){
        this.id = id;
        this.name = name;
    }
    // currently we only have 2 payments
    // 1=alipay, 2=paypal
}
