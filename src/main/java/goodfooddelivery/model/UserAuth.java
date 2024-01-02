package goodfooddelivery.model;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "UserAuth")
@Entity
@Data
public class UserAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserAuthID")
    private Long userAuthId;
    @Column(name = "Name")
    private String name;
    @Column(name = "Email")
    private String email;
    @Column(name = "Password")
    private String password;
    @Column(name = "IsRestaurantOwner")
    private Boolean isRestaurantOwner;
    @Column(name = "IsAdmin")
    private Boolean isAdmin = false;



    public Long getUserAuthId() {
        return userAuthId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

