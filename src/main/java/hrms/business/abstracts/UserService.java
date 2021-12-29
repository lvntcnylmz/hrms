package hrms.business.abstracts;

import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.User;
import hrms.entities.dtos.UserLoginDto;

import java.util.List;

public interface UserService {

    Result login(UserLoginDto userLoginDto);

    DataResult<List<User>> getAll();

    DataResult<User> getById(Integer id);

}
