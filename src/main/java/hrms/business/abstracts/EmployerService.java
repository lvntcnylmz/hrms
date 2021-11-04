package hrms.business.abstracts;

import java.util.List;

import hrms.core.utils.results.DataResult;
import hrms.entities.concretes.Employer;

public interface EmployerService {
    
    DataResult<List<Employer>> getAll();

}
