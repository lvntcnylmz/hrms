package hrms.dataAccess.abstracts;

import hrms.entities.concretes.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
