package hrms.business.abstracts;

import hrms.core.utils.results.Result;
import hrms.entities.concretes.User;

public interface UserService {

    Result login(User user);

    Result delete(Integer id);

}
