package hrms.core.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import hrms.core.security.AppUserDetails;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.stream.Collectors;

@Configuration
public class JwtUtil {

    public String createToken(AppUserDetails appUserDetails) {
        return JWT.create()
                .withIssuer("JobFinder")
                .withSubject(appUserDetails.getUsername())
                .withClaim("roles", appUserDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()));
    }

    public String verifyToken(String token) {
        return JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()))
                .build()
                .verify(token)
                .getSubject();
    }

}


