package hrms.business.concretes;

import hrms.business.abstracts.UserService;
import hrms.core.security.AppUserDetails;
import hrms.core.security.jwt.JwtProperties;
import hrms.core.security.jwt.JwtUtil;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.UserDao;
import hrms.entities.concretes.User;
import hrms.entities.dtos.request.UserLoginDto;
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

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class UserManager implements UserService, UserDetailsService {

    private final UserDao userDao;
    private final JwtUtil jwtUtil;
    private final HttpServletResponse response;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserManager(UserDao userDao, JwtUtil jwtUtil, HttpServletResponse response) {
        this.userDao = userDao;
        this.jwtUtil = jwtUtil;
        this.response = response;
    }

    @Override
    public Result login(UserLoginDto userLoginDto) {

        AppUserDetails userDetails = (AppUserDetails) loadUserByUsername(userLoginDto.getEmail());

        if (!this.passwordEncoder.matches(userLoginDto.getPassword(), userDetails.getPassword())) {
            throw new IncorrectPasswordException("Invalid Password.");
        }

        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(),
                        userLoginDto.getPassword(), userDetails.getAuthorities()));

        String token = this.jwtUtil.createToken(userDetails);

        response.addHeader(JwtProperties.HEADER_STRING,
                JwtProperties.TOKEN_PREFIX + token);

        return new SuccessDataResult<>(token, "Login successful");
    }

    @Override
    public DataResult<List<User>> getAll() {
        return new SuccessDataResult<>(this.userDao.findAll());
    }

    @Override
    public DataResult<User> getById(Integer id) {
        return new SuccessDataResult<User>(this.userDao.findById(id).orElseThrow());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userDao.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User could not found by email."));

        return new AppUserDetails(user);
    }
}
