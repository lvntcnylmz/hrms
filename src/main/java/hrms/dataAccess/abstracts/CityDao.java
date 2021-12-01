package hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.entities.concretes.City;

public interface CityDao extends JpaRepository<City, Integer> {
    
    City findCityById(int id);

}
