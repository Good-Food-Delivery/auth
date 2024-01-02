package goodfooddelivery.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class JwtService {
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    //Validates the JWT token
    public void validateToken(final String token) {
        Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token);
    }

    //Generates a new JWT token
    public String generateToken(String email, List<String> claims) {
        return createToken(claims, email);
    }

    //Constructs the JWT token
    private String createToken(List<String> claims, String email) {

        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("roles", claims);

        return Jwts.builder()
                .claims(claimsMap)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey()).compact();
    }

    //Retrieves the signing key used to sign and verify the JWT token
    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
