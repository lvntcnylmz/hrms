package hrms.core.security;

import hrms.dataAccess.abstracts.UserDao;
import hrms.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailManager implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = this.userDao.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found by email.");
        }

        //return user.map(UserRequestDetail::new).get();

        UserDetails userResponse = org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("ADMIN").build();

        return userResponse;
    }
}
