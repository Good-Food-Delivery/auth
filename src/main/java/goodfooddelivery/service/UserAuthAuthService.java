package goodfooddelivery.service;

import goodfooddelivery.interfaces.IUserAuthService;
import goodfooddelivery.model.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import goodfooddelivery.repository.UserAuthRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserAuthAuthService implements IUserAuthService {

    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public Long saveUserAuth(UserAuth user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserAuth savedUser = userAuthRepository.save(user);
        return savedUser.getUserAuthId();
    }

    public String generateToken(String email)
    {
        List<Map<String, Object>> roles = userAuthRepository.findRolesByEmail(email);
        List<String> claims = new ArrayList<>();

        for (Map<String, Object> role : roles) {
            if ((boolean) role.get("isRestaurantOwner")) {
                claims.add("RESTAURANT_OWNER");
            }
            if ((boolean) role.get("isAdmin")) {
                claims.add("ADMIN");
            }
        }
        return jwtService.generateToken(email, claims);
    }

    public void validateToken(String token)
    {
        jwtService.validateToken(token);
    }

    public void deleteUserAuth(Long id)
    {
        Optional<UserAuth> user = userAuthRepository.findById(id);
        if(user.isPresent()) {
            userAuthRepository.deleteById(id);
        }
    }
}
