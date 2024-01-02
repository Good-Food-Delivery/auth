package goodfooddelivery.repository;
import goodfooddelivery.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {

    Optional<UserAuth> findByEmail(String email);
    @Query("SELECT NEW map(u.isRestaurantOwner AS isRestaurantOwner, u.isAdmin AS isAdmin) FROM UserAuth u WHERE u.email = :email")
    List<Map<String, Object>> findRolesByEmail(String email);
}
