package goodfooddelivery.dto.request;

import lombok.Data;

@Data
public class UserMessage {

    private Long userAuthId;
    private String action;

    public UserMessage(Long userAuthId, String action) {
        this.userAuthId = userAuthId;
        this.action = action;
    }
}
