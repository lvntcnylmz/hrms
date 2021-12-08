package hrms.dataAccess.abstracts;

import hrms.entities.concretes.User;
import hrms.entities.dtos.UserLoginDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {

    Optional<UserLoginDto> findByEmail(String email);

}
