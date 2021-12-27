package hrms.dataAccess.abstracts;

import hrms.entities.concretes.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDao extends JpaRepository<City, Integer> {

}
