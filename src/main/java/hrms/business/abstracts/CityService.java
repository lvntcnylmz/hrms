package hrms.business.abstracts;

import java.util.List;

import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.City;

public interface CityService {
    
    Result add(City city);

    DataResult<List<City>> getAll();
 
    DataResult<City> getCityById(int id);
}
