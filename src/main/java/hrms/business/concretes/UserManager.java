package hrms.business.concretes;

import hrms.business.abstracts.UserService;
import hrms.core.security.AppUserDetails;
import hrms.core.security.PasswordEncoder;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessResult;
import hrms.dataAccess.abstracts.UserDao;
import hrms.entities.concretes.User;
import hrms.entities.dtos.UserLoginDto;
import hrms.exceptions.IncorrectPasswordException;
import hrms.exceptions.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService, UserDetailsService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserManager(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Result login(UserLoginDto userLoginDto) {

        if (!this.passwordEncoder.bCryptPasswordEncoder().matches(userLoginDto.getPassword(),
                loadUserByUsername(userLoginDto.getEmail()).getPassword())) {
            throw new IncorrectPasswordException("Incorrect Password");
        }

        return new SuccessResult("User successfully logged in.");

    }

    @Override
    public Result delete(Integer id) {
        if (this.userDao.existsById(id)) {
            this.userDao.delete(this.userDao.getById(id));
            return new SuccessResult("User deleted");
        }
        throw new UserNotFoundException("User not found by id.");
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userDao.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found by email");
        }

        return new AppUserDetails(user);
    }

}
