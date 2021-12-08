package hrms.core.security;

import hrms.dataAccess.abstracts.UserDao;
import hrms.entities.dtos.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailManager implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<UserLoginDto> user = Optional.ofNullable(this.userDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by email.")));

        //return user.map(UserRequestDetail::new).get();

        UserDetails userResponse = org.springframework.security.core.userdetails.User.withUsername(user.get().getEmail())
                .password(user.get().getPassword())
                .authorities("ADMIN").build();

        return userResponse;
    }
}
