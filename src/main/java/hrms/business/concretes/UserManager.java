package hrms.business.concretes;

import hrms.business.abstracts.UserService;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessResult;
import hrms.dataAccess.abstracts.UserDao;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {

    private final UserDao userDao;

    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Result delete(Integer id) {
        if (this.userDao.existsById(id)) {
            this.userDao.delete(this.userDao.getById(id));
            return new SuccessResult("User deleted");
        }
        throw new UsernameNotFoundException("User not found by id");
    }
}
