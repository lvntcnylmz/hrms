package hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.EmployerService;
import hrms.core.utils.Business.BusinessRules;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.ErrorResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.core.utils.results.SuccessResult;
import hrms.core.verifications.concretes.EmailVerification;
import hrms.dataAccess.abstracts.EmployerDao;
import hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService {

    private EmployerDao employerDao;
    private EmailVerification emailVerification;

    @Autowired
    public EmployerManager(EmployerDao employerDao, EmailVerification emailVerification) {
        this.employerDao = employerDao;
        this.emailVerification = emailVerification;
    }

    @Override
    public Result add(Employer employer) {
        
        var result = BusinessRules.Run(this.emailVerification.verifyEmail(employer.getEmail()), checkIfEmailExists(employer.getEmail()));

        if (result != null) {
            return result;
        }

        return new SuccessDataResult<Employer>(this.employerDao.save(employer), "Employer information was saved.");
    }

    @Override
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), "Employers are listed.");
    }

    private Result checkIfEmailExists(String email){

        var result = this.employerDao.existsByEmailIgnoreCase(email);

        if (result) {
            return new ErrorResult("Email already exists");
        }
        return new SuccessResult("Email valid.");
    }
    
}
