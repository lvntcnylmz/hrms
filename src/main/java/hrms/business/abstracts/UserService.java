package hrms.business.abstracts;

import hrms.core.utils.results.Result;
import hrms.entities.dtos.UserLoginDto;

public interface UserService {

    Result login(UserLoginDto userLoginDto);

    Result delete(Integer id);

}
