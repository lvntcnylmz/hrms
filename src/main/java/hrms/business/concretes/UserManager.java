package hrms.business.concretes;

import hrms.business.abstracts.UserService;
import hrms.core.security.AppUserDetails;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessResult;
import hrms.dataAccess.abstracts.UserDao;
import hrms.entities.concretes.User;
import hrms.entities.dtos.UserLoginDto;
import hrms.exceptions.IncorrectPasswordException;
import hrms.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserManager implements UserService, UserDetailsService {

    private final UserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Result login(UserLoginDto userLoginDto) {

        if (bCryptPasswordEncoder.matches(userLoginDto.getPassword(), loadUserByUsername(userLoginDto.getEmail()).getPassword())) {
            return new SuccessResult("User successfully logged in.");
        }
        throw new IncorrectPasswordException("Incorrect Password");
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
        User user = this.userDao.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found by email."));

        return new AppUserDetails(user);
    }
}
