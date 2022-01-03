package hrms.business.concretes;

import hrms.business.abstracts.EmployerService;
import hrms.core.utils.businessRulesCheck.BusinessRules;
import hrms.core.utils.results.*;
import hrms.core.verifications.concretes.EmailVerification;
import hrms.dataAccess.abstracts.EmployerDao;
import hrms.dataAccess.abstracts.RoleDao;
import hrms.entities.concretes.Employer;
import hrms.entities.concretes.Role;
import hrms.entities.dtos.request.EmployerRegisterDto;
import hrms.entities.dtos.response.EmployerResponseDto;
import hrms.exceptions.EntityNotFoundException;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    public EmployerManager(EmployerDao employerDao,
                           RoleDao roleDao,
                           EmailVerification emailVerification,
                           PasswordEncoder passwordEncoder,
                           ModelMapper modelMapper) {
        this.employerDao = employerDao;
        this.roleDao = roleDao;
        this.emailVerification = emailVerification;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public Result add(EmployerRegisterDto employer) {

        Employer employerRequest = this.modelMapper.map(employer, Employer.class);

        var result = BusinessRules.Run(
                this.emailVerification.verifyEmail(employerRequest.getEmail()),
                this.checkIfEmailExists(employerRequest.getEmail()));

        if (result != null) {
            return result;
        }

        employerRequest.setRoles(addRoleToEmployer());
        employerRequest.setPassword(this.passwordEncoder.encode(employer.getPassword()));
        this.employerDao.save(employerRequest);
        EmployerResponseDto employerResponse = this.modelMapper.map(employerRequest, EmployerResponseDto.class);

        return new SuccessDataResult<>(employerResponse, "Employer information was saved.");
    }

    @Override
    public Result delete(Integer id) {
        if (this.employerDao.existsById(id)) {
            this.employerDao.delete(this.employerDao.getById(id));
            return new SuccessResult("User deleted");
        }
        throw new EntityNotFoundException("User not found by id");
    }

    @Override
    public DataResult<List<EmployerResponseDto>> getAll() {

        List<EmployerResponseDto> employers = this.employerDao.findAll()
                .stream()
                .map(employer -> this.modelMapper.map(employer, EmployerResponseDto.class))
                .toList();

        return new SuccessDataResult<>(employers, "Employers are listed.");
    }

    @Override
    public DataResult<EmployerResponseDto> getById(Integer id) {

        Employer employer = this.employerDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Not Found by Id"));
        EmployerResponseDto employerResponse = this.modelMapper.map(employer, EmployerResponseDto.class);

        return new SuccessDataResult<>(employerResponse, "Employer found by id.");
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
