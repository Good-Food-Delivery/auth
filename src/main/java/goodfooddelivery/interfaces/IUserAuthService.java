package goodfooddelivery.interfaces;

import goodfooddelivery.model.UserAuth;

import java.util.List;

public interface IUserAuthService {
    public Long saveUserAuth(UserAuth user);
    void deleteUserAuth(Long id);
    public String generateToken(String email);
    public void validateToken(String token);

}
