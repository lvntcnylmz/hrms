package hrms.business.concretes;

import hrms.business.abstracts.UserService;
import hrms.core.utils.results.ErrorResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessResult;
import hrms.dataAccess.abstracts.UserDao;
import hrms.entities.dtos.UserLoginDto;
import hrms.exceptions.UserNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserManager implements UserService {

    private final UserDao userDao;

    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Result login(UserLoginDto user) {

        Optional<UserLoginDto> userDb = Optional.ofNullable(this.userDao.findByEmail(user.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found by email.")));

        if (passwordEncoder().matches(user.getPassword(), userDb.get().getPassword())) {
            return new SuccessResult("You logged in.");
        }
        return new ErrorResult("Incorrect Password");
    }

    @Override
    public Result delete(Integer id) {
        if (this.userDao.existsById(id)) {
            this.userDao.delete(this.userDao.getById(id));
            return new SuccessResult("User deleted");
        }
        throw new UserNotFoundException("User not found by id.");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
