package hrms.business.concretes;

import hrms.business.abstracts.CityService;
import hrms.core.utils.messages.Message;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.CityDao;
import hrms.entities.concretes.City;
import hrms.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityManager implements CityService {

    private final CityDao cityDao;

    public CityManager(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public Result add(City city) {
        return new SuccessDataResult<City>(this.cityDao.save(city), Message.SAVED);
    }

    @Override
    public DataResult<List<City>> getAll() {
        return new SuccessDataResult<List<City>>(this.cityDao.findAll(), Message.LISTED);
    }

    @Override
    public DataResult<City> getCityById(int id) {
        return new SuccessDataResult<City>(this.cityDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Message.NOT_FOUND)), Message.NOT_FOUND);
    }

}
