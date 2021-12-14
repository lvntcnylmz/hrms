package hrms.business.concretes;

import hrms.business.abstracts.EmployerService;
import hrms.core.utils.Business.BusinessRules;
import hrms.core.utils.results.*;
import hrms.core.verifications.concretes.EmailVerification;
import hrms.dataAccess.abstracts.EmployerDao;
import hrms.entities.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerManager implements EmployerService {

    private final EmployerDao employerDao;
    private final EmailVerification emailVerification;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmployerManager(EmployerDao employerDao,
                           EmailVerification emailVerification,
                           PasswordEncoder passwordEncoder) {
        this.employerDao = employerDao;
        this.emailVerification = emailVerification;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Result add(Employer employer) {

        var result = BusinessRules.Run(this.emailVerification.verifyEmail(employer.getEmail()),
                checkIfEmailExists(employer.getEmail()));

        if (result != null) {
            return result;
        }

        employer.setPassword(this.passwordEncoder.encode(employer.getPassword()));
        return new SuccessDataResult<Employer>(this.employerDao.save(employer), "Employer information was saved.");
    }

    @Override
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), "Employers are listed.");
    }

    private Result checkIfEmailExists(String email) {

        var result = this.employerDao.existsByEmailIgnoreCase(email);

        if (result) {
            return new ErrorResult("Email already exists");
        }
        return new SuccessResult("Email valid.");
    }

}
