package hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.CityService;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.CityDao;
import hrms.entities.concretes.City;

@Service
public class CityManager implements CityService {

    private CityDao cityDao;

    @Autowired
    public CityManager(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public Result add(City city) {
        return new SuccessDataResult<City>(this.cityDao.save(city), "Thi city info has added.");
    }

    @Override
    public DataResult<List<City>> getAll() {
        return new SuccessDataResult<List<City>>(this.cityDao.findAll(), "All cities listed.");
    }
    
}
