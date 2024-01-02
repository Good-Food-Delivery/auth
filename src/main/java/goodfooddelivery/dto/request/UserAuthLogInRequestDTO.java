package goodfooddelivery.dto.request;

import lombok.Data;

@Data
public class UserAuthLogInRequestDTO {
    private String email;
    private String password;


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
