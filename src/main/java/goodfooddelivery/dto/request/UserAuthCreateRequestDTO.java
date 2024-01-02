package goodfooddelivery.dto.request;

import lombok.Data;

@Data
public class UserAuthCreateRequestDTO {

    private String name;
    private String email;
    private String password;
    private Boolean isRestaurantOwner;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }



}
