package xyz.kmahyyg.eshopdemo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.ALWAYS)
@Data
public class PublicResponse {
    @JsonProperty("status")
    private int status;

    @JsonProperty("message")
    private String message;

    public PublicResponse(int status, String message){
        this.status = status;
        this.message = message;
    }
}
