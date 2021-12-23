package hrms.business.abstracts;

import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.Employer;

import java.util.List;

public interface EmployerService {

    Result add(Employer employer);

    Result delete(Integer id);

    DataResult<List<Employer>> getAll();

    DataResult<Employer> getById(int id);

}
