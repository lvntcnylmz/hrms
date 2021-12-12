package hrms.business.concretes;

import hrms.business.abstracts.JobSeekerService;
import hrms.core.utils.Business.BusinessRules;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.core.utils.results.SuccessResult;
import hrms.core.verifications.abstracts.MernisVerificationService;
import hrms.dataAccess.abstracts.JobSeekerDao;
import hrms.entities.concretes.JobSeeker;
import hrms.exceptions.EmailAlreadyExistsException;
import hrms.exceptions.NationalIdAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerManager implements JobSeekerService {

    private final JobSeekerDao jobSeekerDao;
    private final UserManager userManager;
    private final MernisVerificationService mernisVerificationService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public JobSeekerManager(JobSeekerDao jobSeekerDao,
                            UserManager userManager,
                            MernisVerificationService mernisVerificationService) {
        this.jobSeekerDao = jobSeekerDao;
        this.userManager = userManager;
        this.mernisVerificationService = mernisVerificationService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Result add(JobSeeker jobSeeker) {

        Result result = BusinessRules.Run(
                this.mernisVerificationService.checkIfRealPerson(jobSeeker),
                this.checkIfNationalIdAlreadyExists(jobSeeker),
                this.checkIfEmailAlreadyExists(jobSeeker));

        if (result != null) {
            return result;
        }

        jobSeeker.setPassword(this.passwordEncoder.encode(jobSeeker.getPassword()));
        return new SuccessDataResult<JobSeeker>(this.jobSeekerDao.save(jobSeeker), "User information is valid. User was saved.");
    }

    @Override
    public Result delete(Integer id) {
        if (this.jobSeekerDao.existsById(id)) {
            this.jobSeekerDao.delete(this.jobSeekerDao.getById(id));
            this.userManager.delete(id);
            return new SuccessResult("User deleted");
        }
        throw new UsernameNotFoundException("User not found by id");
    }

    @Override
    public DataResult<List<JobSeeker>> getAll() {
        return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerDao.findAll(), "Candidates are listed.");
    }

    private Result checkIfNationalIdAlreadyExists(JobSeeker jobSeeker) {

        var result = this.jobSeekerDao.existsJobSeekerByNationalId(jobSeeker.getNationalId());

        if (result) {
            throw new NationalIdAlreadyExistsException("National-ID already exists.");
        }
        return new SuccessResult();
    }

    private Result checkIfEmailAlreadyExists(JobSeeker jobSeeker) {
        var result = this.jobSeekerDao.existsJobSeekerByEmail(jobSeeker.getEmail());

        if (result) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }
        return new SuccessResult();
    }

}
