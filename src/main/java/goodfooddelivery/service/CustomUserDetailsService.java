package goodfooddelivery.service;

import goodfooddelivery.config.CustomUserDetails;
import goodfooddelivery.model.UserAuth;
import goodfooddelivery.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAuthRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserAuth> credentials = repository.findByEmail(email);
        return credentials.map(CustomUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("User with email: "+email+" was not found"));
    }
}
