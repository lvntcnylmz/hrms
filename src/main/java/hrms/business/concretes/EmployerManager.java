package hrms.business.concretes;

import hrms.business.abstracts.EmployerService;
import hrms.core.utils.businessRulesCheck.BusinessRules;
import hrms.core.utils.results.*;
import hrms.core.verifications.concretes.EmailVerification;
import hrms.dataAccess.abstracts.EmployerDao;
import hrms.dataAccess.abstracts.RoleDao;
import hrms.dataAccess.abstracts.UserDao;
import hrms.entities.concretes.Employer;
import hrms.entities.concretes.Role;
import hrms.exceptions.JobNotFoundException;
import hrms.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class EmployerManager implements EmployerService {

    private final EmployerDao employerDao;
    private final UserDao userDao;
    private final EmailVerification emailVerification;
    private final PasswordEncoder passwordEncoder;
    private final RoleDao roleDao;

    @Autowired
    public EmployerManager(EmployerDao employerDao,
                           UserDao userDao, RoleDao roleDao,
                           EmailVerification emailVerification,
                           PasswordEncoder passwordEncoder) {
        this.employerDao = employerDao;
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.emailVerification = emailVerification;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Result add(Employer employer) {

        var result = BusinessRules.Run(
                this.emailVerification.verifyEmail(employer.getEmail()),
                this.checkIfEmailExists(employer.getEmail()));

        if (result != null) {
            return result;
        }

        employer.setRoles(addRoleToEmployer());
        employer.setPassword(this.passwordEncoder.encode(employer.getPassword()));
        return new SuccessDataResult<Employer>(this.employerDao.save(employer), "Employer information was saved.");
    }

    @Override
    public Result delete(Integer id) {
        if (this.employerDao.existsById(id)) {
            this.employerDao.delete(this.employerDao.getById(id));
            this.userDao.deleteById(id);
            return new SuccessResult("User deleted");
        }
        throw new UserNotFoundException("User not found by id");
    }

    @Override
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), "Employers are listed.");
    }

    @Override
    public DataResult<Employer> getById(int id) {
        return new SuccessDataResult<Employer>(this.employerDao.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Not Found")), "Employer found by id.");
    }

    private Result checkIfEmailExists(String email) {

        var result = this.employerDao.existsByEmailIgnoreCase(email);

        if (result) {
            return new ErrorResult("Email already exists");
        }
        return new SuccessResult("Email valid.");
    }

    private Collection<Role> addRoleToEmployer() {

        return new ArrayList<>() {
            {
                add(roleDao.findByName("USER"));
                add(roleDao.findByName("EMPLOYER"));
            }
        };
    }

}
