package hrms.business.concretes;

import hrms.business.abstracts.EmployerService;
import hrms.core.utils.Business.BusinessRules;
import hrms.core.utils.results.*;
import hrms.core.verifications.concretes.EmailVerification;
import hrms.dataAccess.abstracts.EmployerDao;
import hrms.dataAccess.abstracts.RoleDao;
import hrms.entities.concretes.Employer;
import hrms.entities.concretes.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class EmployerManager implements EmployerService {

    private final EmployerDao employerDao;
    private final EmailVerification emailVerification;
    private final PasswordEncoder passwordEncoder;
    private final RoleDao roleDao;

    @Autowired
    public EmployerManager(EmployerDao employerDao,
                           RoleDao roleDao,
                           EmailVerification emailVerification,
                           PasswordEncoder passwordEncoder) {
        this.employerDao = employerDao;
        this.roleDao = roleDao;
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

        employer.setRoles(addRoleToEmployer());
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

    private Collection<Role> addRoleToEmployer() {

        Collection<Role> roles = new ArrayList<>() {
            {
                add(roleDao.findByName("ROLE_USER"));
                add(roleDao.findByName("ROLE_EMPLOYER"));
            }
        };

        return roles;
    }

}
