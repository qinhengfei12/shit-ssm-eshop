package xyz.kmahyyg.eshopdemo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude()
@Data
public class DeliveryResponse {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;

    public DeliveryResponse(int id, String name){
        this.id = id;
        this.name = name;
    }
    // currently we only have three deliveries
    // 1=JD,2=SF,3=Others
}
