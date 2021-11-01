package hrms.business.abstracts;

import java.util.List;

import hrms.entities.concretes.Employer;

public interface EmployerService {
    
    List<Employer> getAll();

}
