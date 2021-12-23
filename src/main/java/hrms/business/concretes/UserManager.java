package hrms.business.concretes;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import hrms.business.abstracts.UserService;
import hrms.core.security.AppUserDetails;
import hrms.core.security.PasswordEncoder;
import hrms.core.security.jwt.JwtProperties;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.UserDao;
import hrms.entities.concretes.User;
import hrms.entities.dtos.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserManager implements UserService, UserDetailsService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserManager(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Result login(UserLoginDto userLoginDto) {

        UserDetails userDetails = loadUserByUsername(userLoginDto.getEmail());

        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword(), userDetails.getAuthorities())
            );
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception.getLocalizedMessage());
        }

        return new SuccessDataResult<>(this.createToken((AppUserDetails) userDetails), "Token created.");
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User could not found by email."));

        return new AppUserDetails(user);
    }

    @Override
    public String createToken(AppUserDetails appUserDetails) {

        return JWT.create()
                .withSubject(appUserDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()));
    }
}
