package hrms.business.abstracts;

import java.util.List;

import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.Employer;

public interface EmployerService {
    
    Result add(Employer employer);

    DataResult<List<Employer>> getAll();

}
