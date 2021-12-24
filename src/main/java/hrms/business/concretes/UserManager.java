package hrms.business.concretes;

import hrms.business.abstracts.UserService;
import hrms.core.security.AppUserDetails;
import hrms.core.security.jwt.JwtUtil;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.UserDao;
import hrms.entities.concretes.User;
import hrms.entities.dtos.UserLoginDto;
import hrms.exceptions.IncorrectPasswordException;
import hrms.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService, UserDetailsService {

    private final UserDao userDao;

    @Autowired
    private AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserManager(UserDao userDao, JwtUtil jwtUtil) {
        this.userDao = userDao;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Result login(UserLoginDto userLoginDto) {

        UserDetails userDetails = loadUserByUsername(userLoginDto.getEmail());

        if (!this.passwordEncoder.matches(userLoginDto.getPassword(), userDetails.getPassword())) {
            throw new IncorrectPasswordException("Invalid Password.");
        }

        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(),
                        userLoginDto.getPassword(), userDetails.getAuthorities()));

        return new SuccessDataResult<>(this.jwtUtil.createToken((AppUserDetails) userDetails), "Login successful");
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userDao.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User could not found by email."));

        return new AppUserDetails(user);
    }
}
