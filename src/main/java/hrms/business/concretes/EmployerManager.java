package hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.EmployerService;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.EmployerDao;
import hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService {

    private EmployerDao employerDao;

    @Autowired
    public EmployerManager(EmployerDao employerDao) {
        this.employerDao = employerDao;
    }

    @Override
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), "Employers are listed.");
    }
    
}
