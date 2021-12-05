package hrms.core.security;

import hrms.dataAccess.abstracts.UserDao;
import hrms.entities.concretes.User;
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

        Optional<User> user = this.userDao.findByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found by email"));

        return user.map(UserRequestDetail::new).get();
    }
}
